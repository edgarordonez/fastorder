package app.fastorder.fastorder.api

import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import app.fastorder.fastorder.drinks.infrastructure.dependency_injection.DrinkModuleDependencyContainer
import app.fastorder.fastorder.food.infrastructure.dependency_injection.FoodModuleDependencyContainer
import app.fastorder.fastorder.order.infrastructure.dependency_injection.OrderModuleDependencyContainer
import app.fastorder.fastorder.waiters.infrastructure.dependency_injection.WaiterModuleDependencyContainer
import app.fastorder.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import app.fastorder.shared.infrastructure.doobie.JdbcConfig

object WebServer {
  def start(): Unit = {
    val appConfig       = ConfigFactory.load("application")
    val actorSystemName = appConfig.getString("main-actor-system.name")

    val serverConfig = ConfigFactory.load("http-server")
    val host         = serverConfig.getString("http-server.host")
    val port         = serverConfig.getInt("http-server.port")

    val dbConfig = JdbcConfig(appConfig.getConfig("database"))

    val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig)

    implicit val system: ActorSystem                        = sharedDependencies.system
    implicit val materializer: ActorMaterializer            = sharedDependencies.materializer
    implicit val executionContext: ExecutionContextExecutor = sharedDependencies.executionContext

    val container = new EntryPointDependencyContainer(
      new WaiterModuleDependencyContainer(sharedDependencies.doobieDbConnection),
      new DrinkModuleDependencyContainer(sharedDependencies.doobieDbConnection),
      new FoodModuleDependencyContainer(sharedDependencies.doobieDbConnection),
      new OrderModuleDependencyContainer(sharedDependencies.doobieDbConnection)
    )

    val routes = new Routes(container)

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
