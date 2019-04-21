package app.fastorder.fastorder.api

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.json.JsValue

final class Routes(container: EntryPointDependencyContainer) {
  private val status = get {
    path("status")(container.statusGetController.get())
  }

  private val waiter = get {
    path("waiters")(container.waiterGetController.get())
  } ~
    post {
      path("waiters") {
        jsonBody { body =>
          container.waiterPostController.post(
            body("id").convertTo[String],
            body("name").convertTo[String]
          )
        }
      }
    }

  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))

  val all: Route = status ~ waiter
}
