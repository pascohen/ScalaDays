package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.Global
import scala.reflect.internal.Phase
import scala.tools.nsc.transform.Transform
import scala.tools.nsc.transform._

object LogAdderComponent {
  def apply(name: String, global: Global) = new LogAdderComponent(name, global)
}

class LogAdderComponent(val name: String, val global: Global) extends PluginComponent with Transform {

  val runsAfter = List[String]("parser");
  override val description = "Add logging component"

  val phaseName = "logAdder"

  def newTransformer(unit: global.CompilationUnit) = new LogAdderTransformer(unit)

  class LogAdderTransformer(unit: global.CompilationUnit) extends global.Transformer {
    import global._

    def handle(m: Tree, t:TermName,arguments:List[Tree]): Tree = {
      val methodName = t.toString
      val args = arguments.map( t => t.toString).mkString("-")
      val a1 = q"""
      println("Before calling "+$methodName+" - "+$args)
      val t1 = System.currentTimeMillis()
      val r = $m     
      val t2 = System.currentTimeMillis()  
      println("After calling "+$methodName+" - "+$args)
      println("Took "+(t2-t1))
      r
      """
      a1
    }

    def postTransform(tree: global.Tree): global.Tree = {
      tree match {
        case Apply(Ident(t@TermName("myMethod")), args) =>
          handle(tree,t,args)
        case ValDef(m, i, t, f @ Apply(Ident(tm@TermName("myMethod")), args)) =>
          ValDef(m, i, t, handle(f,tm,args))
        case _ =>
          tree
      }
    }

    override def transform(tree: global.Tree): global.Tree = {
      postTransform(super.transform(tree))
    }
  }
}
