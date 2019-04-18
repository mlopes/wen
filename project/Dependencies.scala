import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

  lazy val wenDependencies = Seq(
    "eu.timepit"            %% "refined"                        % "0.9.4"
  )
}
