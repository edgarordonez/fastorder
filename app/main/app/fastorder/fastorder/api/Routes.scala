package app.fastorder.fastorder.api

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

final class Routes(container: EntryPointDependencyContainer) {
  private val status = get {
    path("status")(container.statusGetController.get())
  }

  private val waiter = get {
    path("waiters")(container.waiterGetController.get())
  }

  val all: Route = status ~ waiter
}
