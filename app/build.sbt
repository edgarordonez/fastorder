Config.commonSettings

Compile / scalaSource := baseDirectory.value / "main/"
Test / scalaSource := baseDirectory.value / "test/"
Compile / resourceDirectory := baseDirectory.value / "conf"

libraryDependencies ++= Dependencies.shared

fork in run := true
connectInput in run := true

mainClass in assembly := Some("app.fastorder.Initializer")
assemblyJarName in assembly := "fastorder.jar"
assemblyOutputPath in assembly := baseDirectory.value / ".." / "package" / (assemblyJarName in assembly).value
test in assembly := {}
