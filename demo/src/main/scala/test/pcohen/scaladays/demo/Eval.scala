package test.pcohen.scaladays.demo

import scala.tools.nsc._
import scala.tools.nsc.interpreter._

class Eval() {
  
  def eval(content:String,out: java.io.PrintWriter) = {
    val s = new Settings
    s.usejavacp.value=true
    val n = new IMain(s,out)
    n.eval(content)
  }
}
