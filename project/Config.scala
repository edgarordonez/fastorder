import sbt.{Tests, _}
import sbt.Keys._

object Config {
  val commonSettings = Seq(
    organization := "app.fastorder",
    scalaVersion := "2.13.2",
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
