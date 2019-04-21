package app.fastorder.fastorder.drink.domain

import java.util.UUID

object DrinkId {
  def apply(value: String): DrinkId = DrinkId(UUID.fromString(value))
}

case class DrinkId(value: UUID)
