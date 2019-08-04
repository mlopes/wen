import sbt._

object Dependencies {

  val scalaTestVersion = "3.0.8"
  val scalaCheckVersion = "1.14.0"
  val refinedVersion = "0.9.8"
  val catsVersion = "2.0.0-M4"
  val circeVersion = "0.12.0-M4"


  lazy val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion
   ,"org.scalactic" %% "scalactic" % scalaTestVersion
   ,"org.scalacheck" %% "scalacheck" % scalaCheckVersion
   ,"eu.timepit" %% "refined-scalacheck" % refinedVersion
  ).map(_ % Test)

  lazy val catsDependencies = Seq(
    "org.typelevel" %% "cats-core" % catsVersion
  )

  lazy val refinedDependencies = Seq(
    "eu.timepit" %% "refined" % refinedVersion
  )

  lazy val circeDependencies = Seq(
    "io.circe" %% "circe-core" % circeVersion % Optional
  )

  lazy val circeExtraDependencies = Seq (
    "io.circe" %% "circe-literal" % circeVersion
   ,"io.circe" %% "circe-generic" % circeVersion
   ,"org.typelevel" %% "jawn-parser" % "0.14.2"
  )
}
