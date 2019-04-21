package app.fastorder.shared.infrastructure.dependency_injection

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import app.fastorder.shared.infrastructure.doobie.{DoobieDbConnection, JdbcConfig}

import scala.concurrent.ExecutionContextExecutor

final class SharedModuleDependencyContainer(actorSystemName: String, jdbConfig: JdbcConfig) {
  implicit val system: ActorSystem               = ActorSystem(actorSystemName)
  val materializer: ActorMaterializer            = ActorMaterializer()
  val executionContext: ExecutionContextExecutor = system.dispatcher

  val doobieDbConnection: DoobieDbConnection = new DoobieDbConnection(jdbConfig)
}
