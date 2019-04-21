package app.fastorder.fastorder.api

import app.fastorder.fastorder.api.controller.status.StatusGetController
import app.fastorder.fastorder.api.controller.waiter.{WaiterGetController, WaiterPostController}
import app.fastorder.fastorder.waiter.infrastructure.dependency_injection.WaiterModuleDependencyContainer

final class EntryPointDependencyContainer(waiterDependencies: WaiterModuleDependencyContainer) {
  val statusGetController = new StatusGetController

  val waiterGetController = new WaiterGetController(waiterDependencies.waiterSearcher)
  val waiterPostController = new WaiterPostController(waiterDependencies.waiterRegister)
}
