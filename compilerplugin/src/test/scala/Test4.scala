object Test4 extends App {
  def f(x: Int) = x + 1
  def g(s: String) = s + "_suffix"

  val i = f(10)
  val j = g("b")

  println("I = " + i)
  println("J = " + j)
}
