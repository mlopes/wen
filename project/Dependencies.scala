import sbt._

object Dependencies {
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
   ,"org.scalactic" %% "scalactic" % "3.0.5" % Test
   ,"org.scalacheck" %% "scalacheck" % "1.14.0" % Test
   ,"eu.timepit" %% "refined-scalacheck" % "0.9.5" % Test
   ,"eu.timepit" %% "refined" % "0.9.5"
   ,"org.typelevel" %% "cats-core" % "1.6.0"
  )

  lazy val wenDependencies = Seq(
    "eu.timepit" %% "refined" % "0.9.5"
    ,"org.typelevel" %% "cats-core" % "1.6.0"
  )
}
