package app.fastorder.fastorder.food.domain

import scala.concurrent.Future

trait FoodRepository {
  def all(): Future[Seq[Food]]
  def save(food: Food): Future[Unit]
}
