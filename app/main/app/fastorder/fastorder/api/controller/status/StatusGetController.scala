package app.fastorder.fastorder.api.controller.status

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute

final class StatusGetController {
  def get(): StandardRoute = complete(HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}"""))
}
