package app.fastorder.fastorder.api

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{get, path}

final class Routes(container: EntryPointDependencyContainer) {
  private val status = get {
    path("status")(container.statusGetController.get())
  }

  val all: Route = status
}
