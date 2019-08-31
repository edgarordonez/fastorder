package app.fastorder.fastorder.drinks.application.search

import scala.concurrent.Future
import app.fastorder.fastorder.drinks.domain.{Drink, DrinkRepository}

final class DrinkSearcher(repository: DrinkRepository) {
  def all(): Future[Seq[Drink]] = repository.all()
}
