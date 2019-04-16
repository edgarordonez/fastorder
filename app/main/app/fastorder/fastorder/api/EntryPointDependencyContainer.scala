package app.fastorder.fastorder.api

import app.fastorder.fastorder.api.controller.status.StatusGetController
import app.fastorder.fastorder.api.controller.waiter.WaiterGetController

final class EntryPointDependencyContainer {
  val statusGetController = new StatusGetController

  val waiterGetController = new WaiterGetController
}
