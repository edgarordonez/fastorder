name := "fastorder"
version := "0.1"

disablePlugins(AssemblyPlugin)

lazy val shared = Project(id = "shared", base = file("src/shared"))
lazy val fastorder =
  Project(id = "fastorder", base = file("src/fastorder")).dependsOn(shared % "compile->compile;test->test")
lazy val fastcontrol =
  Project(id = "fastcontrol", base = file("src/fastcontrol")).dependsOn(shared % "compile->compile;test->test")

lazy val root = (project in file(".")).aggregate(app, shared, fastcontrol, fastorder)

lazy val app = Project(id = "app", base = file("app/"))
  .dependsOn(fastorder % "compile->compile;test->test")
  .dependsOn(fastcontrol % "compile->compile;test->test")
  .dependsOn(shared % "compile->compile;test->test")

lazy val pack = taskKey[Unit]("Packages application as a fat jar")
pack := {
  (assembly in app).toTask.value
}

addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("tq", "testQuick")
addCommandAlias("tsf", "testShowFailed")

addCommandAlias("c", "compile")
addCommandAlias("tc", "test:compile")

addCommandAlias("f", "scalafmt")
addCommandAlias("fc", "scalafmtCheck")
addCommandAlias("tf", "test:scalafmt")
addCommandAlias("tfc", "test:scalafmtCheck")

addCommandAlias("prep", ";c;tc;fc;tfc")

test in assembly := {}
