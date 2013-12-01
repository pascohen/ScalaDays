object Test3 extends App {
  def f(x: Int) = x + 1
  def g(s: String) = s + "_suffix"

  10 assign_to i1
  f(10) assign_to j1
  "a" assign_to k1
  g("b") assign_to l1

  println(i1 + " - " + j1 + " - " + k1 + " - " + l1)

  10 --> i2
  f(10) --> j2
  "a" ==> k2
  g("b") ==> l2

  println(i2 + " - " + j2 + " - " + k2 + " - " + l2)
}
