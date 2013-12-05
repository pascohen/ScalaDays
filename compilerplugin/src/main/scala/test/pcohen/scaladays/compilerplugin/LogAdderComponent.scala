package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.Global
import scala.reflect.internal.Phase
import scala.tools.nsc.transform.Transform
import scala.tools.nsc.transform._

object LogAdderComponent {
  def apply(name: String, global: Global) = new LogAdderComponent(name, global)
}

class LogAdderComponent(val name: String, val global: Global) extends PluginComponent with Transform with TypingTransformers {

  val runsAfter = List[String]("refchecks");
  override val description = "Add logging component"

  val phaseName = "logAdder"

  def newTransformer(unit: global.CompilationUnit) = new LogAdderTransformer(unit)

  class LogAdderTransformer(unit: global.CompilationUnit) extends TypingTransformer(unit) {
    import global._
    import scala.reflect.runtime.{ universe => ru }
    //import scala.reflect.macros.Universe

    val systemSymbol = global.findMemberFromRoot(global.TermName(classOf[java.lang.System].getName))
    //val errorSymbol = global.typeOf[java.lang.RuntimeException]

    //val predef = global.typeOf[scala.Predef].termSymbol

    def handle(m: Tree): Tree = {
      println(" === Handling " + m)
      /*
      val a1 = q"""
      $systemSymbol.out.println("Ok")
      val t1 = $systemSymbol.currentTimeMillis()
      $systemSymbol.out.println("Ok "+$t1)
      $m     
      val t2 = $systemSymbol.currentTimeMillis()  
      11
      """
  */

      val t11 = newTermName("t1")
      val ta = ValDef(Modifiers(), t11, TypeTree(), Apply(Select(Ident(systemSymbol), TermName("currentTimeMillis")), List()))

      //val r = global.newTermName("result")

//      val sym = global.findMemberFromRoot(r)
      
      //val call = ValDef(Modifiers(), r, TypeTree(), m)
   
      
      //m.asInstanceOf[MethodSymbol].owner.info.decls.enter(sym)

      val tb = ValDef(Modifiers(), TermName("t2"), TypeTree(), Apply(Select(Ident(systemSymbol), TermName("currentTimeMillis")), List()))

      //global.treeCopy
      /*
val a1 =  Block(List(ta , call , tb, 

    Apply(Select(Select(Ident(systemSymbol),TermName("out")), TermName("println")), List(Apply(Select(Literal(Constant(" ==== Took ")), TermName("$plus")), List(Apply(Select(Ident(TermName("t2")), TermName("$minus")), 
List(Ident(t1)))))))) , Ident(TermName("r")))
*/

      /*
import scala.tools.reflect.ToolBox
 val mk = ru.runtimeMirror(this.getClass().getClassLoader()).mkToolBox()
 println(" Got mk "+mk)
 val s = mk.parse("println(2)")
 println("======================= "+s)
 * */

      val a1 = Block(List(ta, m, tb,
        Apply(Select(Select(Ident(systemSymbol), TermName("out")), TermName("println")),
          List(Ident(t11)))), Literal(Constant(1)))
          
      typer.typed(a1)
    }

    def postTransform(tree: global.Tree): global.Tree = {
      tree match {
        case Apply(fun, _) =>
          fun.symbol match {
            case m: global.MethodSymbol =>
              if (fun.symbol.nameString.contains("myMeth")) {
                handle(tree)
              } else tree
            case _ => tree
          }

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
        case ValDef(m, t, tr, f @ Apply(Select(_, TermName(n)), _)) =>
          println("C " + n)
     /*     if (n.contains("myMeth"))
            ValDef(m, t, tr, handle(f))
          else tree*/
          tree
        case _ =>
          tree
      }
    }

    override def transform(tree: global.Tree): global.Tree = {
      postTransform(super.transform(tree))
    }
  }
}
