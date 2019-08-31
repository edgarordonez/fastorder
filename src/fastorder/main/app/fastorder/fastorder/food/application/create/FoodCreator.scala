package app.fastorder.fastorder.food.application.create

import app.fastorder.fastorder.shared.domain.price.Price
import app.fastorder.fastorder.food.domain.{Food, FoodId, FoodName, FoodRepository}

final class FoodCreator(repository: FoodRepository) {
  def create(id: FoodId, name: FoodName, price: Price): Unit = {
    val food: Food = Food(id, name, price)

    repository.save(food)
  }
}
