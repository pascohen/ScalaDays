package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.Global
import scala.reflect.internal.Phase
import scala.tools.nsc.transform.Transform

object LogAdderComponent {
  def apply(name: String, global: Global) = new LogAdderComponent(name, global)
}

class LogAdderComponent(val name: String, val global: Global) extends PluginComponent with Transform {

  val runsAfter = List[String]("refchecks");
  override val description = "Add logging component"

  val phaseName = "logAdder"

  def newTransformer(unit: global.CompilationUnit) = new LogAdderTransformer(unit)

  class LogAdderTransformer(unit: global.CompilationUnit) extends global.Transformer {
    import global._

    def handle(m: Tree): Tree = {
      println(" === Handling " + m)

      /*val a1 = q"""
      val t1 = System.currentTimeMillis();println("T1")
      $m
     val t2 = System.currentTimeMillis();println("T2")
     println("Call of "+m+" took "+((t2-t1)/1000))
      """*/
      
      val a1 = q"""
          val t1:Int = 22
          $m
          val t2:Int = 43
          
    """
      typer.typed(a1)
    }

    def postTransform(tree: global.Tree): global.Tree = {
      tree match {
        /* case Apply(fun, _) =>
          fun.symbol match {
            case m: global.MethodSymbol =>
              if (fun.symbol.nameString.contains("myMeth")) { //Honnetement c est nimp
                handle(tree)
              } else tree
            case _ => tree
          }*/
        case ValDef(_, _, _, Ident(TermName(n))) =>
          println("A " + n)
          if (n.contains("myMeth"))
            handle(tree)
          else tree
        case ValDef(_, _, _, Apply(Ident(TermName(n)), _)) =>
          println("B " + n)
          if (n.contains("myMeth"))
            handle(tree)
          else tree
        case ValDef(_, _, _, Apply(Select(_, TermName(n)), _)) =>
          println("C " + n)
          if (n.contains("myMeth"))
            handle(tree)
          else tree
        case _ =>
          //println("C "+global.showRaw(tree))
          tree
      }
    }

    override def transform(tree: global.Tree): global.Tree = {
      postTransform(super.transform(tree))
    }
  }
}