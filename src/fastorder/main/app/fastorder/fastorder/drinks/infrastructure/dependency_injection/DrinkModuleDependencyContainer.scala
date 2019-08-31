package app.fastorder.fastorder.drinks.infrastructure.dependency_injection

import app.fastorder.fastorder.drinks.application.create.DrinkCreator
import app.fastorder.fastorder.drinks.application.search.DrinkSearcher
import app.fastorder.fastorder.drinks.infrastructure.repository.DoobieMySqlDrinkRepository
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class DrinkModuleDependencyContainer(doobieDbConnection: DoobieDbConnection)(
  implicit executionContext: ExecutionContext
) {
  val repository: DoobieMySqlDrinkRepository = new DoobieMySqlDrinkRepository(doobieDbConnection)

  val drinkSearcher = new DrinkSearcher(repository)
  val drinkCreator  = new DrinkCreator(repository)
}
