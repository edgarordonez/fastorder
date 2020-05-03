disablePlugins(AssemblyPlugin)

Config.commonSettings

Compile / scalaSource := baseDirectory.value / "main/"
Test / scalaSource := baseDirectory.value / "test/"
Compile / resourceDirectory := baseDirectory.value / "conf"

libraryDependencies ++= Dependencies.shared

test in assembly := {}
