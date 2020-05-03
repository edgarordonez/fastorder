package app.fastorder.fastorder.orders.application.create

import app.fastorder.fastorder.orders.domain._
import app.fastorder.fastorder.shared.domain.waiter.WaiterId

final class OrderCreator(repository: OrderRepository) {
  def create(
    id: OrderId,
    waiterId: WaiterId,
    table: Int,
    drinks: Seq[OrderDrink],
    food: Seq[OrderFood]
  ): Unit = {
    val amount: Double = drinks.map(drink => drink.price * drink.quantity).sum + food
        .map(plate => plate.price * plate.quantity)
        .sum
    val order: Order = Order(id, waiterId, table, drinks, food, amount)

    repository.save(order)
  }
}
