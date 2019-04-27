ThisBuild / organization := "dev.mlopes"
ThisBuild / organizationName := "dev.mlopes"
ThisBuild / organizationHomepage := Some(url("http://marco-lopes.com/"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/mlopes/wen"),
    "scm:git@github.com:mlopes/wen.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "mlopes",
    name  = "Marco Lopes",
    email = "marco@mlop.es",
    url   = url("http://marco-lopes.com")
  )
)

ThisBuild / description := "Date and time types and instances"
ThisBuild / licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("https://github.com/mlopes/wen"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true
