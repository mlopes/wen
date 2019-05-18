import ReleaseTransformations._

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

val releaseNotesFile = taskKey[File]("Release notes for current version")
releaseNotesFile in ThisBuild := {
  val currentVersion = (version in ThisBuild).value
  file("notes") / s"$currentVersion.markdown"
}

val ensureReleaseNotesExists = taskKey[Unit]("Ensure release notes exists")
ensureReleaseNotesExists in ThisBuild := {
  val currentVersion = (version in ThisBuild).value
  val notes = releaseNotesFile.value
  if(!notes.isFile) {
    throw new IllegalStateException(s"no release notes found for version [$currentVersion] at [$notes].")
  }
}

val ensureVersionUpdateOnReadme = taskKey[Unit]("Ensure README reflects the version correctly")
ensureVersionUpdateOnReadme in ThisBuild := {
  val haystack: List[String] = IO.readLines(file("README.md"))
  val needle: String =  s"""libraryDependencies += "dev.mlopes" %% "wen" % "${(version in ThisBuild).value}""""
  val count: Int =  haystack.filter(l => l.contains(needle)).size
  if(count != 1) {
    throw new IllegalStateException(
      s"README.md hasn't been updated with: $needle"
    )
  }
}

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

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runClean,                               // : ReleaseStep
  runTest,                                // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  releaseStepTask(ensureVersionUpdateOnReadme), // Makes sure that the README is up to date
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  releaseStepTask(ensureReleaseNotesExists in ThisBuild), // Aborts the release if release notes are missing
  tagRelease,                             // : ReleaseStep
  publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  releaseStepCommand("sonatypeRelease"), // Removes the need to log into sonatype to release to maven central
  pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
)
