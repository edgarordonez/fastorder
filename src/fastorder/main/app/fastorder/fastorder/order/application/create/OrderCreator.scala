package app.fastorder.fastorder.order.application.create

import app.fastorder.fastorder.order.domain.{Order, OrderId, OrderDrink, OrderRepository}
import app.fastorder.fastorder.shared.domain.waiter.WaiterId

final class OrderCreator(repository: OrderRepository) {
  def create(
    id: OrderId,
    waiterId: WaiterId,
    table: Int,
    drinks: Seq[OrderDrink],
    food: String,
    amount: Double
  ): Unit = {
    val order: Order = Order(id, waiterId, table, drinks, food, amount)

    repository.save(order)
  }
}
