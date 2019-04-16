package app.fastorder.fastorder.shared.domain.waiter

import java.util.UUID

object WaiterId {
  def apply(value: String): WaiterId = WaiterId(UUID.fromString(value))
}

case class WaiterId(value: UUID)
