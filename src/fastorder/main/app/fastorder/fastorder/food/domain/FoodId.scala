package app.fastorder.fastorder.food.domain

import java.util.UUID

object FoodId {
  def apply(value: String): FoodId = FoodId(UUID.fromString(value))
}

case class FoodId(value: UUID)
