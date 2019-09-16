package app.fastorder.fastorder.api

import java.io.InputStream
import java.security.{KeyStore, SecureRandom}
import javax.net.ssl.{KeyManagerFactory, SSLContext, TrustManagerFactory}
import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor
import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.{ConnectionContext, Http, HttpsConnectionContext}
import akka.stream.ActorMaterializer
import app.fastorder.fastorder.drinks.infrastructure.dependency_injection.DrinkModuleDependencyContainer
import app.fastorder.fastorder.food.infrastructure.dependency_injection.FoodModuleDependencyContainer
import app.fastorder.fastorder.order.infrastructure.dependency_injection.OrderModuleDependencyContainer
import app.fastorder.fastorder.waiters.infrastructure.dependency_injection.WaiterModuleDependencyContainer
import app.fastorder.shared.infrastructure.dependency_injection.SharedModuleDependencyContainer
import app.fastorder.shared.infrastructure.doobie.JdbcConfig
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings

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

    // Manual HTTPS configuration
    val password: Array[Char] = "fastorder".toCharArray // do not store passwords in code, read them from somewhere safe!

    val ks: KeyStore = KeyStore.getInstance("PKCS12")
    val keystore: InputStream = getClass.getClassLoader.getResourceAsStream("app/fastorder/fastorder/api/server.p12")

    require(keystore != null, "Keystore required!")
    ks.load(keystore, password)

    val keyManagerFactory: KeyManagerFactory = KeyManagerFactory.getInstance("SunX509")
    keyManagerFactory.init(ks, password)

    val tmf: TrustManagerFactory = TrustManagerFactory.getInstance("SunX509")
    tmf.init(ks)

    val sslContext: SSLContext = SSLContext.getInstance("TLS")
    sslContext.init(keyManagerFactory.getKeyManagers, tmf.getTrustManagers, new SecureRandom)
    val https: HttpsConnectionContext = ConnectionContext.https(sslContext)

    val container = new EntryPointDependencyContainer(
      new WaiterModuleDependencyContainer(sharedDependencies.doobieDbConnection),
      new DrinkModuleDependencyContainer(sharedDependencies.doobieDbConnection),
      new FoodModuleDependencyContainer(sharedDependencies.doobieDbConnection),
      new OrderModuleDependencyContainer(sharedDependencies.doobieDbConnection)
    )

    val settingsCors = CorsSettings.defaultSettings.withAllowGenericHttpRequests(false)
    val routes = {
      import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors

      cors(settingsCors) {
        new Routes(container).all ~ new SSE().all
      }
    }

    Http().setDefaultServerHttpContext(https)
    val bindingFuture = Http().bindAndHandle(routes, host, port, connectionContext = https)

    bindingFuture.failed.foreach { _ =>
      println(s"Failed to bind to https://$host:$port/:")
    }

    println(s"Server online at https://$host:$port/\nPress RETURN to stop...")

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
