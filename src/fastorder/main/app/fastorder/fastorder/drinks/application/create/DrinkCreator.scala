package app.fastorder.fastorder.drinks.application.create

import app.fastorder.fastorder.shared.domain.price.Price
import app.fastorder.fastorder.drinks.domain.{Drink, DrinkId, DrinkName, DrinkRepository}

final class DrinkCreator(repository: DrinkRepository) {
  def create(id: DrinkId, name: DrinkName, price: Price): Unit = {
    val drink: Drink = Drink(id, name, price)

    repository.save(drink)
  }
}
