organization := "test.pcohen.scaladays"

name := "compilerPlugin"

version := "1.0"

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.11.0-SNAPSHOT"

scalacOptions += "-feature"

scalacOptions += "-deprecation"

libraryDependencies += "org.scala-lang"%"scala-compiler"%"2.11.0-SNAPSHOT"
