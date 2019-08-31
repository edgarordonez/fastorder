package app.fastorder.fastorder.order.domain

import java.util.UUID

object OrderId {
  def apply(value: String): OrderId = OrderId(UUID.fromString(value))
}

case class OrderId(value: UUID)
