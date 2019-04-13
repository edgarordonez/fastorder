package app.fastorder.fastorder.api

import app.fastorder.fastorder.api.controller.status.StatusGetController

final class EntryPointDependencyContainer {
  val statusGetController = new StatusGetController
}
