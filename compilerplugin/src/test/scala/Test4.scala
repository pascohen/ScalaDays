class C {
 def myMethod(i: Int) = {
   println("Calling myMethod with "+i)
   i + 1
}

def call() = {
	myMethod(10)
}
}

object Test4 extends App {
 
val c = new C
c.call()
}
