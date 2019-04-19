import sbt._

object Dependencies {
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
    ,"org.scalactic" %% "scalactic" % "3.0.5" % Test
    , "org.scalacheck" %% "scalacheck" % "1.14.0" % Test
  )

  lazy val wenDependencies = Seq(
    "eu.timepit"            %% "refined"                        % "0.9.4"
  )
}
