import sbt._

object Dependencies {

  val scalaTestVersion = "3.0.7"
  val scalaCheckVersion = "1.14.0"
  val refinedVersion = "0.9.5"
  val catsVersion = "1.6.0"
  val circeVersion = "0.11.1"
  val avro4sVersion = "2.0.4"


  lazy val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion
   ,"org.scalactic" %% "scalactic" % scalaTestVersion
   ,"org.scalacheck" %% "scalacheck" % scalaCheckVersion
   ,"eu.timepit" %% "refined-scalacheck" % refinedVersion
  ).map(_ % Test)

  lazy val catsDependencies = Seq(
    "org.typelevel" %% "cats-core" % catsVersion
  )

  lazy val avro4sDependencies = Seq(
    "com.sksamuel.avro4s" %% "avro4s-core" % avro4sVersion
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
