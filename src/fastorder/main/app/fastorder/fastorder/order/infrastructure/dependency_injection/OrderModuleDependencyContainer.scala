package app.fastorder.fastorder.order.infrastructure.dependency_injection

import app.fastorder.fastorder.order.application.create.OrderCreator
import app.fastorder.fastorder.order.application.search.OrderSearcher
import app.fastorder.fastorder.order.infrastructure.repository.DoobieMySqlOrderRepository
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class OrderModuleDependencyContainer(doobieDbConnection: DoobieDbConnection)(
  implicit executionContext: ExecutionContext
) {
  val repository: DoobieMySqlOrderRepository = new DoobieMySqlOrderRepository(doobieDbConnection)

  val orderSearcher: OrderSearcher = new OrderSearcher(repository)
  val orderCreator: OrderCreator   = new OrderCreator(repository)
}
