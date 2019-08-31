import sbt.{Tests, _}
import sbt.Keys._

object Configuration {
  val commonSettings = Seq(
    organization := "app.fastorder",
    scalaVersion := "2.12.8",
    scalacOptions := {
      val default = Seq(
        "-deprecation",
        "-feature",
        "-unchecked",
        "-Xlint",
        "-Ywarn-dead-code",
        "-Ywarn-unused",
        "-Ywarn-unused-import",
        "-Xcheckinit"
      )
      if (version.value.endsWith("SNAPSHOT")) {
        default :+ "-Xcheckinit"
      } else { default }
    },
    scalacOptions in (Test, console) --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings"),
    scalacOptions in (Test, console) ++= Seq("-Ywarn-unused:-imports"),
    javaOptions += "-Duser.timezone=UTC",
    fork in Test := true,
    parallelExecution in Test := false,
    testForkedParallel in Test := false,
    testOptions in Test ++= Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"),
      Tests.Argument("-oDF")
    ),
    cancelable in Global := true,
    exportJars := true
  )
}
