package app.fastorder.fastorder.waiters.infrastructure.dependency_injection

import app.fastorder.fastorder.waiters.application.register.WaiterRegister
import app.fastorder.fastorder.waiters.application.search.WaiterSearcher
import app.fastorder.fastorder.waiters.infrastructure.repository.DoobieMySqlWaiterRepository
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class WaiterModuleDependencyContainer(doobieDbConnection: DoobieDbConnection)(
  implicit executionContext: ExecutionContext
) {
  val repository: DoobieMySqlWaiterRepository = new DoobieMySqlWaiterRepository(doobieDbConnection)

  val waiterSearcher: WaiterSearcher = new WaiterSearcher(repository)
  val waiterRegister: WaiterRegister = new WaiterRegister(repository)
}
