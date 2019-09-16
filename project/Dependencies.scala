import sbt._

object Dependencies {
  object Versions {
    val Akka              = "2.5.25"
    val AkkaHttp          = "10.1.9"
    val AkkaHttpSprayJson = "10.1.9"
    val NScalaTime        = "2.22.0"
    val Pprint            = "0.5.4"
    val ScalaTest         = "3.0.8"
    val Doobie            = "0.6.0"
    val MysqlConnector    = "8.0.15"
    val circeVersion = "0.11.1"
  }

  val shared = Seq(
    "com.github.nscala-time" %% "nscala-time"          % Versions.NScalaTime,
    "com.lihaoyi"            %% "pprint"               % Versions.Pprint,
    "com.typesafe.akka"      %% "akka-actor"           % Versions.Akka,
    "com.typesafe.akka"      %% "akka-stream"          % Versions.Akka,
    "com.typesafe.akka"      %% "akka-http"            % Versions.AkkaHttp,
    "com.typesafe.akka"      %% "akka-http-spray-json" % Versions.AkkaHttpSprayJson,
    "org.tpolecat"           %% "doobie-core"          % Versions.Doobie,
    "ch.megard"              %% "akka-http-cors"       % "0.4.1",
    "io.circe"               %% "circe-core"           % Versions.circeVersion,
    "io.circe"               %% "circe-generic"        % Versions.circeVersion,
    "io.circe"               %% "circe-parser"         % Versions.circeVersion,
    "mysql"                  % "mysql-connector-java"  % Versions.MysqlConnector,
    "org.postgresql"         % "postgresql"            % "42.2.6",
    "org.scalatest"          %% "scalatest"            % Versions.ScalaTest % Test,
    "com.typesafe.akka"      %% "akka-testkit"         % Versions.Akka      % Test,
    "com.typesafe.akka"      %% "akka-http-testkit"    % Versions.AkkaHttp  % Test
  )

  val fastorder = Seq()

  val fastcontrol = Seq()
}
