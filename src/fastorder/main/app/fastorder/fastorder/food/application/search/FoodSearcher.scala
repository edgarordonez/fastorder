package app.fastorder.fastorder.food.application.search

import scala.concurrent.Future
import app.fastorder.fastorder.food.domain.{Food, FoodRepository}

final class FoodSearcher(repository: FoodRepository) {
  def all(): Future[Seq[Food]] = repository.all()
}
