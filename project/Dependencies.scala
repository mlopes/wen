import sbt._

object Dependencies {

  val scalaTestVersion = "3.0.7"
  val scalaCheckVersion = "1.14.0"
  val refinedVersion = "0.9.5"
  val catsVersion = "1.6.0"
  val circeVersion = "0.10.0"


  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
   ,"org.scalactic" %% "scalactic" % scalaTestVersion % Test
   ,"org.scalacheck" %% "scalacheck" % scalaCheckVersion % Test
   ,"eu.timepit" %% "refined-scalacheck" % refinedVersion % Test
   ,"eu.timepit" %% "refined" % refinedVersion
   ,"org.typelevel" %% "cats-core" % catsVersion
  )

  lazy val wenDependencies = Seq(
    "eu.timepit" %% "refined" % refinedVersion
    ,"org.typelevel" %% "cats-core" % catsVersion
    ,"io.circe" %% "circe-core" % circeVersion % "Optional"
  )
}
