import sbt._

object Dependencies {
  object Versions {
    val Akka              = "2.6.5"
    val AkkaHttp          = "10.1.11"
    val AkkaHttpSprayJson = "10.1.11"
    val NScalaTime        = "2.24.0"
    val Pprint            = "0.5.6"
    val ScalaTest         = "3.1.1"
    val Doobie            = "0.9.0"
    val MysqlConnector    = "8.0.20"
    val circeVersion      = "0.13.0"
  }

  val shared = Seq(
    "com.github.nscala-time" %% "nscala-time"          % Versions.NScalaTime,
    "com.lihaoyi"            %% "pprint"               % Versions.Pprint,
    "com.typesafe.akka"      %% "akka-actor"           % Versions.Akka,
    "com.typesafe.akka"      %% "akka-stream"          % Versions.Akka,
    "com.typesafe.akka"      %% "akka-http"            % Versions.AkkaHttp,
    "com.typesafe.akka"      %% "akka-http-spray-json" % Versions.AkkaHttpSprayJson,
    "org.tpolecat"           %% "doobie-core"          % Versions.Doobie,
    "io.circe"               %% "circe-core"           % Versions.circeVersion,
    "io.circe"               %% "circe-generic"        % Versions.circeVersion,
    "io.circe"               %% "circe-parser"         % Versions.circeVersion,
    "mysql"                  % "mysql-connector-java"  % Versions.MysqlConnector,
    "org.postgresql"         % "postgresql"            % "42.2.12",
    "org.scalatest"          %% "scalatest"            % Versions.ScalaTest % Test,
    "com.typesafe.akka"      %% "akka-testkit"         % Versions.Akka      % Test,
    "com.typesafe.akka"      %% "akka-http-testkit"    % Versions.AkkaHttp  % Test
  )

  val fastorder = Seq()

  val fastcontrol = Seq()
}
