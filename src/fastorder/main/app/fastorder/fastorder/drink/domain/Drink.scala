package app.fastorder.fastorder.drink.domain

import app.fastorder.fastorder.shared.domain.price.Price

object Drink {
  def apply(id: String, name: String, price: String): Drink = Drink(DrinkId(id), DrinkName(name), Price(price))
}

case class Drink(id: DrinkId, name: DrinkName, price: Price)
