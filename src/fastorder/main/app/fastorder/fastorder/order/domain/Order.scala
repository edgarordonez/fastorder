package app.fastorder.fastorder.order.domain

import app.fastorder.fastorder.shared.domain.waiter.WaiterId

object Order {
  def apply(
    id: String,
    waiterId: String,
    table: Int,
    drinks: String,
    food: String,
    amount: Double
  ): Order =
    Order(
      OrderId(id),
      WaiterId(waiterId),
      table,
      drinks,
      food,
      amount
    )
}

case class Order(
  id: OrderId,
  waiterId: WaiterId,
  table: Int,
  drinks: String,
  food: String,
  amount: Double
)
