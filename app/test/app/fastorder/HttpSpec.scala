package app.fastorder

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import app.fastorder.fastorder.api.{EntryPointDependencyContainer, Routes}
import app.fastorder.fastorder.waiter.infrastructure.dependency_injection.WaiterModuleDependencyContainer
import app.fastorder.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import app.fastorder.shared.infrastructure.doobie.JdbcConfig
import com.typesafe.config.ConfigFactory

abstract class HttpSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  val appConfig       = ConfigFactory.load("application")
  val actorSystemName = appConfig.getString("main-actor-system.name")

  val dbConfig = JdbcConfig(appConfig.getConfig("acceptance-tests-database"))

  val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig)

  val container = new EntryPointDependencyContainer(
    new WaiterModuleDependencyContainer(sharedDependencies.doobieDbConnection)
  )
  val routes = new Routes(container)

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
