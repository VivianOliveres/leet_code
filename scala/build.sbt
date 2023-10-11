import Dependencies._

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.kensai"

val circeVersion = "0.14.5"
val testVersion = "3.2.15"

lazy val root = (project in file("."))
  .settings(
    name := "Leet Code",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-simple" % "1.7.36",
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "org.scalactic" %% "scalactic" % testVersion,
      "org.scalatest" %% "scalatest" % testVersion % "test",
      "org.scalatest" %% "scalatest-flatspec" % testVersion % "test",
      "org.scalatestplus" %% "junit-4-13" % "3.2.15.0" % "test",
      "org.scalatestplus" %% "scalacheck-1-16" % "3.2.14.0" % "test",
      "org.typelevel" %% "cats-core" % "2.9.0",
      "com.lihaoyi" %% "os-lib" % "0.9.1"
    )
  )

