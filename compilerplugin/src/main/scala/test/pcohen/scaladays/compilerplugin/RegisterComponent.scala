package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.Global
import scala.reflect.internal.Phase
import scala.collection.mutable.Map;
import scala.collection.mutable.ArrayBuffer

object RegisterComponent {
  def apply(name: String, global: Global) = new RegisterComponent(name, global)
}

class RegisterComponent(val name: String, val global: Global) extends PluginComponent {

  val runsAfter = List[String]("refchecks");
  override val description = "Register API usage component"

  val phaseName = "register"
  def newPhase(prev: Phase): Phase = new RegisterPhase(prev)

  class RegisterPhase(prev: Phase) extends StdPhase(prev) {
    override def name = RegisterComponent.this.name

    def apply(unit: global.CompilationUnit) {
      new RegisterTraverser().traverse(unit.body)
    }

    class RegisterTraverser extends global.Traverser {

      val register = Map[(global.Symbol, global.TermName), ArrayBuffer[global.Position]]()
      val demoApi = global.typeOf[demo.DemoApi]

      def checkAndRegister(owner: global.Symbol, typ: global.Type, name: global.TermName, pos: global.Position) = {
        if (typ <:< demoApi) {
          val k = (owner, name)

          if (!register.contains(k)) {
            register(k) = ArrayBuffer()
          }
          register(k).append(pos)
          println("==== Registering " + k.toString() + " in " + pos)
        }
      }

      override def traverse(tree: global.Tree): Unit = tree match {
        case global.Apply(fun, args) =>
          fun.symbol match {
            case m: global.MethodSymbol =>
              checkAndRegister(m.owner, m.owner.tpe, m.name, tree.pos)
            case _ => ()
          }
          super.traverse(fun)
          super.traverseTrees(args)
        case global.Select(subTree, name) =>
          subTree.symbol match {
            case m: global.MethodSymbol =>
              checkAndRegister(m.returnType.typeSymbol, m.returnType, name.toTermName, tree.pos)
            case _ => ()
          }
          super.traverse(subTree)
        case _ => super.traverse(tree)
      }
    }
  }
}