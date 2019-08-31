package app.fastorder.fastorder.drinks.domain

import java.util.UUID

object DrinkId {
  def apply(value: String): DrinkId = DrinkId(UUID.fromString(value))
}

case class DrinkId(value: UUID)
