package app.fastorder.fastorder.order.domain

import app.fastorder.fastorder.shared.domain.waiter.WaiterId

object Order {
  def apply(id: String, waiterId: WaiterId, table: Int, drinks: Seq[OrderDrink], Food: Seq[OrderFood]): Order = Order()
}

case class Order()
