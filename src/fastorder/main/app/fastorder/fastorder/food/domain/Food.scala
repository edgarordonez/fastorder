package app.fastorder.fastorder.food.domain

import app.fastorder.fastorder.shared.domain.price.Price

object Food {
  def apply(id: String, name: String, price: Double): Food = Food(FoodId(id), FoodName(name), Price(price))
}

case class Food(id: FoodId, name: FoodName, price: Price)
