package app.fastorder.fastorder.order.domain

object OrderDrink {
  def apply(id: String, name: String): OrderDrink = OrderDrink(id, name)
}

case class OrderDrink(id: String, name: String)
