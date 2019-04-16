import sbt._

object Dependencies {
  object Versions {
    val Akka              = "2.5.22"
    val AkkaHttp          = "10.1.8"
    val AkkaHttpSprayJson = "10.1.8"
    val NScalaTime        = "2.22.0"
    val Pprint            = "0.5.4"
    val ScalaTest         = "3.0.5"
  }

  val shared = Seq(
    "com.github.nscala-time" %% "nscala-time"          % Versions.NScalaTime,
    "com.lihaoyi"            %% "pprint"               % Versions.Pprint,
    "com.typesafe.akka"      %% "akka-actor"           % Versions.Akka,
    "com.typesafe.akka"      %% "akka-stream"          % Versions.Akka,
    "com.typesafe.akka"      %% "akka-http"            % Versions.AkkaHttp,
    "com.typesafe.akka"      %% "akka-http-spray-json" % Versions.AkkaHttpSprayJson,
    "org.scalatest"          %% "scalatest"            % Versions.ScalaTest % Test,
    "com.typesafe.akka"      %% "akka-testkit"         % Versions.Akka      % Test,
    "com.typesafe.akka"      %% "akka-http-testkit"    % Versions.AkkaHttp  % Test
  )

  val fastorder = Seq()

  val fastcontrol = Seq()
}
