package app.fastorder.fastorder.order.domain

object OrderFood {
  def apply(id: String, name: String): OrderFood = OrderFood(id, name)
}

case class OrderFood(id: String, name: String)
