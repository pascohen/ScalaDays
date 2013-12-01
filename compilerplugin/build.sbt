organization := "test.pcohen.scaladays"

name := "compilerPlugin"

version := "1.0"

scalaVersion := "2.11.0-M5"

scalacOptions += "-feature"

scalacOptions += "-deprecation"

libraryDependencies += "org.scala-lang"%"scala-compiler"%"2.11.0-M5"

libraryDependencies += "org.scala-lang"%"scala-reflect"%"2.11.0-M5"

libraryDependencies += "test.pcohen.scaladays"%%"examplelib"%"1.0"
