package app.fastorder.fastorder.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import app.fastorder.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer

import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor
import com.typesafe.config.ConfigFactory

object WebServer {
  def start(): Unit = {
    val appConfig       = ConfigFactory.load("application")
    val actorSystemName = appConfig.getString("main-actor-system.name")

    val serverConfig = ConfigFactory.load("http-server")
    val host         = serverConfig.getString("http-server.host")
    val port         = serverConfig.getInt("http-server.port")

    val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName)

    implicit val system: ActorSystem                        = sharedDependencies.system
    implicit val materializer: ActorMaterializer            = sharedDependencies.materializer
    implicit val executionContext: ExecutionContextExecutor = sharedDependencies.executionContext

    val container = new EntryPointDependencyContainer
    val routes    = new Routes(container)

    val bindingFuture = Http().bindAndHandle(routes.all, host, port)

    bindingFuture.failed.foreach { _ =>
      println(s"Failed to bind to http://$host:$port/:")
    }

    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")

    StdIn.readLine()

    println("Stopping server...")

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => {
        sharedDependencies.system.terminate()
        println("Server stopped!")
      })
  }
}
