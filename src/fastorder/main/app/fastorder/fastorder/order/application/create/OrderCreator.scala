package app.fastorder.fastorder.order.application.create

import app.fastorder.fastorder.shared.domain.waiter.WaiterId
import app.fastorder.fastorder.order.domain.{Order, OrderId, OrderRepository}

final class OrderCreator(repository: OrderRepository) {
  def create(
    id: OrderId,
    waiterId: WaiterId,
    table: Int,
    drinks: String,
    food: String,
    amount: Double
  ): Unit = {
    val order: Order = Order(id, waiterId, table, drinks, food, amount)

    repository.save(order)
  }
}
