package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.Global
import scala.reflect.internal.Phase
import scala.tools.nsc.transform.Transform

object LogAdderComponent {
  def apply(name: String, global: Global) = new LogAdderComponent(name, global)
}

class LogAdderComponent(val name: String, val global: Global) extends PluginComponent with Transform 
{

  val runsAfter = List[String]("refchecks");
  override val description = "Add logging component"

  val phaseName = "logAdder"

  def newTransformer(unit: global.CompilationUnit) = new LogAdderTransformer(unit)

  class LogAdderTransformer(unit: global.CompilationUnit) extends global.Transformer {
    import global._

    def handle(m: Tree): Tree = {
      println(" === Handling " + m)
      //val a1 = typer.typed(reify {val t1 = System.currentTimeMillis();println("T1") }.tree)
      //val a2 = typer.typed(reify {val t2 = System.currentTimeMillis();println("T2") }.tree)
      //val a3 = reify { println("Call of "+m+" took "+((t2-t1)/1000)) }
      val a1 = reify { println("T1") }.tree
      /*      val resultTermName = TermName("result")*/
     //m
     // typer.typed(a1)
     //typer.typed(a1.tree)
      //a1
      Block()
    }

    def postTransform(tree: global.Tree): global.Tree = {
      tree match {
        case Apply(fun, _) =>
          fun.symbol match {
            case m: global.MethodSymbol =>
              if (!(m.owner.tpe =:= tree.tpe)) { //Honnetement c est nimp
              handle(tree)
              } else tree
            case _ => tree
          }
        case _ => tree
      }
    }

    override def transform(tree: global.Tree): global.Tree = {
      postTransform(super.transform(tree))
    }
  }
}