package app.fastorder.fastorder.drinks.domain

import app.fastorder.fastorder.shared.domain.price.Price

object Drink {
  def apply(id: String, name: String, price: Double): Drink = Drink(DrinkId(id), DrinkName(name), Price(price))
}

case class Drink(id: DrinkId, name: DrinkName, price: Price)
