
object Test4 extends App {
 
  def myMethod(i: Int) = {
   println("Calling myMethod with "+i)
   i + 1
}
  
  myMethod(2)

val i = myMethod(210)

println(i)
}
