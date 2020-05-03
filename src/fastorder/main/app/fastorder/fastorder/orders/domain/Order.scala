package app.fastorder.fastorder.orders.domain

import app.fastorder.fastorder.shared.domain.waiter.WaiterId

object Order {
  def apply(
    id: String,
    waiterId: String,
    table: Int,
    drinks: Seq[OrderDrink],
    food: Seq[OrderFood],
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
  drinks: Seq[OrderDrink],
  food: Seq[OrderFood],
  amount: Double
)
