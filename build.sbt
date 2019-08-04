import Dependencies._

lazy val scala213 = "2.13.0"
lazy val scala212 = "2.12.8"

ThisBuild / scalaVersion     := scala213
ThisBuild / organization     := "dev.mlopes"
ThisBuild / organizationName := "mlopes"

lazy val wen = project
  .in(file("."))
  .aggregate(core, cats, circe)
  .settings(name := "Wen Root")
  .settings(
    crossScalaVersions := Nil,
    publish := {},
    publishArtifact := false
  )

lazy val core = project
  .in(file("modules/core"))
  .settings(moduleName := "wen", name := "Wen", description := "Idiomatic Date and Time types")
  .settings(
    libraryDependencies ++= refinedDependencies,
    libraryDependencies ++= testDependencies ++ catsDependencies.map(_ % Test),
    defaultConfig,
    versionConfig
  )

lazy val cats = project
  .in(file("modules/cats"))
  .dependsOn(core)
  .settings(moduleName := "wen-cats", name := "Wen Cats", description := "Cats instances for Wen")
  .settings(
    libraryDependencies ++= catsDependencies,
    libraryDependencies ++= testDependencies,
    defaultConfig,
    versionConfig
  )

lazy val circe = project
  .in(file("modules/circe"))
  .dependsOn(core % "compile->compile;test->test", cats)
  .settings(moduleName := "wen-circe", name := "Wen Circe", description := "Circe encoders and decoders for Wen types")
  .settings(
    libraryDependencies ++= circeDependencies,
    libraryDependencies ++= testDependencies ++ circeExtraDependencies.map(_ % Test),
    defaultConfig,
    versionConfig
  )

lazy val defaultConfig = Seq(
  compile in Compile := (compile in Compile).dependsOn(dependencyUpdates).value,
  coverageMinimum := 100,
  coverageFailOnMinimum := true,
  publishTo := sonatypePublishTo.value
)

lazy val versionConfig = Seq(
  scalaVersion := scala213,
  crossScalaVersions := List(scala213, scala212)
)

