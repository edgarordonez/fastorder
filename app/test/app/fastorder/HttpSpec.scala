package app.fastorder

import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.concurrent.ScalaFutures
import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import app.fastorder.fastorder.api.{EntryPointDependencyContainer, Routes}
import app.fastorder.fastorder.drinks.infrastructure.dependency_injection.DrinkModuleDependencyContainer
import app.fastorder.fastorder.food.infrastructure.dependency_injection.FoodModuleDependencyContainer
import app.fastorder.fastorder.orders.infrastructure.dependency_injection.OrderModuleDependencyContainer
import app.fastorder.fastorder.waiters.infrastructure.dependency_injection.WaiterModuleDependencyContainer
import app.fastorder.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import app.fastorder.shared.infrastructure.doobie.{DoobieDbConnection, JdbcConfig}

abstract class HttpSpec extends AnyWordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  val appConfig: Config = ConfigFactory.load("application")
  val actorSystemName: String = appConfig.getString("main-actor-system.name")

  val dbConfig: JdbcConfig = JdbcConfig(appConfig.getConfig("acceptance-tests-database"))

  val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig)

  val container = new EntryPointDependencyContainer(
    new WaiterModuleDependencyContainer(sharedDependencies.doobieDbConnection),
    new DrinkModuleDependencyContainer(sharedDependencies.doobieDbConnection),
    new FoodModuleDependencyContainer(sharedDependencies.doobieDbConnection),
    new OrderModuleDependencyContainer(sharedDependencies.doobieDbConnection)
  )

  val routes = new Routes(container)

  protected val doobieDbConnection: DoobieDbConnection = new DoobieDbConnection(dbConfig)

  protected def posting[T](path: String, request: String)(body: ⇒ T): T =
    HttpRequest(
      method = HttpMethods.POST,
      uri = path,
      entity = HttpEntity(
        MediaTypes.`application/json`,
        ByteString(request)
      )
    ) ~> routes.all ~> check(body)

  protected def getting[T](path: String)(body: ⇒ T): T = Get(path) ~> routes.all ~> check(body)
}
