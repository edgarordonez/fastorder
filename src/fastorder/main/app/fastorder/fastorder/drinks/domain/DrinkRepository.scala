package app.fastorder.fastorder.drinks.domain

import scala.concurrent.Future

trait DrinkRepository {
  def all(): Future[Seq[Drink]]
  def save(drinks: Drink): Future[Unit]
}
