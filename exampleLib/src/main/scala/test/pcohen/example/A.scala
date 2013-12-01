package test.pcohen.example

object A {
  def authorizedObjectTask = println("I am an authorized object A task")
  def unAuthorizedObjectTask = println("I am an unauthorized object A task")
  
  def authorizedObjectTask2() = println("I am an authorized object A task")
  def unAuthorizedObjectTask2() = println("I am an unauthorized object A task")
}

class A(val s: String) {
  def authorizedTask = println("I am an authorized A task for " + s)
  def unAuthorizedTask = println("I am an unauthorized A task for " + s)
  
  def authorizedTask2() = println("I am an authorized A task for " + s)
  def unAuthorizedTask2() = println("I am an unauthorized A task for " + s)
}