package app.fastorder.fastorder.food.infrastructure.dependency_injection

import app.fastorder.fastorder.food.application.create.FoodCreator
import app.fastorder.fastorder.food.application.search.FoodSearcher
import app.fastorder.fastorder.food.infrastructure.repository.DoobieMySqlFoodRepository
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class FoodModuleDependencyContainer(doobieDbConnection: DoobieDbConnection)(
  implicit executionContext: ExecutionContext
) {
  val repository: DoobieMySqlFoodRepository = new DoobieMySqlFoodRepository(doobieDbConnection)

  val foodSearcher: FoodSearcher = new FoodSearcher(repository)
  val foodCreator: FoodCreator   = new FoodCreator(repository)
}
