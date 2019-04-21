package app.fastorder.fastorder.waiter.infrastructure.dependency_injection

import scala.concurrent.{ExecutionContext}
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection
import app.fastorder.fastorder.waiter.infrastructure.repository.DoobieMySqlWaiterRepository
import app.fastorder.fastorder.waiter.application.search.WaiterSearcher
import app.fastorder.fastorder.waiter.application.register.WaiterRegister
import app.fastorder.fastorder.waiter.domain.WaiterRepository

final class WaiterModuleDependencyContainer(doobieDbConnection: DoobieDbConnection)(implicit executionContext: ExecutionContext) {
  val repository: WaiterRepository = new DoobieMySqlWaiterRepository(doobieDbConnection)

  val waiterSearcher: WaiterSearcher = new WaiterSearcher(repository)
  val waiterRegister: WaiterRegister = new WaiterRegister(repository)
}
