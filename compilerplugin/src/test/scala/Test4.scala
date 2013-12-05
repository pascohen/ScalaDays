object Test4 extends App {
  def myMeth1(x: Int) = x + 1
  def myMeth2(s: String) = s + "_suffix"

  val i = myMeth1(10)
  val j = myMeth2("b")

  println("I = " + i)
  println("J = " + j)
}
