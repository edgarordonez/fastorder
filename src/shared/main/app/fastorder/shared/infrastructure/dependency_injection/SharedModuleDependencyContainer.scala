package app.fastorder.shared.infrastructure.dependency_injection

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContextExecutor

final class SharedModuleDependencyContainer(actorSystemName: String) {
  implicit val system: ActorSystem               = ActorSystem(actorSystemName)
  val materializer: ActorMaterializer            = ActorMaterializer()
  val executionContext: ExecutionContextExecutor = system.dispatcher
}
