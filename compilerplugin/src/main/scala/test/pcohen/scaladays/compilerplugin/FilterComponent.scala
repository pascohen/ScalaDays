package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.Global
import scala.reflect.internal.Phase

object FilterComponent {
  def apply(name: String, global: Global) = new FilterComponent(name, global)
}

class FilterComponent(val name: String, val global: Global) extends PluginComponent {

  val runsAfter = List[String]("refchecks");
  override val description = "Check usage of prohibited calls component"

  val phaseName = "filter"
  def newPhase(prev: Phase): Phase = new FilterPhase(prev)

  class FilterPhase(prev: Phase) extends StdPhase(prev) {
    override def name = FilterComponent.this.name

    def apply(unit: global.CompilationUnit) {
      new FilterTraverser().traverse(unit.body)

      class FilterTraverser extends global.Traverser {

        val systemObject = global.findMemberFromRoot(global.TermName(classOf[java.lang.System].getName)).tpe
        val rootObject = global.typeOf[test.pcohen.example.A.type]
        val rootClass = global.typeOf[test.pcohen.example.A]

        def check(owner: global.Symbol, typ: global.Type, name: global.TermName, pos: global.Position) = {
          if (((typ <:< rootClass) || (typ <:< rootObject))
            && (name.startsWith("unAuthorized"))) {
            unit.error(pos, "Calling " + name + " in " + owner + " is forbidden")
          } else if ((typ <:< systemObject) &&
            (name.toString().equals("exit"))) {
            unit.error(pos, "Calling " + name + " in " + owner + " is forbidden")
          }
        }
        override def traverse(tree: global.Tree): Unit = tree match {
          case global.Apply(fun, args) =>
            fun.symbol match {
              case m: global.MethodSymbol =>
                check(m.owner, m.owner.tpe, m.name, tree.pos)
            }
            super.traverse(fun)
            super.traverseTrees(args)
          case global.Select(subTree, name) =>
            subTree.symbol match {
              case m: global.MethodSymbol =>
                check(m.returnType.typeSymbol, m.returnType, name.toTermName, tree.pos)
              case _ =>
                check(subTree.symbol, subTree.tpe, name.toTermName, tree.pos)
            }
            super.traverse(subTree)

          case _ => super.traverse(tree)
        }
      }
    }
  }
}