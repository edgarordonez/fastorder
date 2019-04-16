package app.fastorder.fastorder.api.controller.waiter

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute

final class WaiterGetController {
  def get(): StandardRoute = complete(HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}"""))
}
