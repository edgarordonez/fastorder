package app.fastorder.fastorder.api

import app.fastorder.fastorder.api.controller.status.StatusGetController
import app.fastorder.fastorder.api.controller.waiter.{WaiterGetController, WaiterPostController}
import app.fastorder.fastorder.api.controller.drinks.{DrinksGetController, DrinksPostController}
import app.fastorder.fastorder.api.controller.food.{FoodGetController, FoodPostController}
import app.fastorder.fastorder.api.controller.order.{OrderGetController, OrderPostController}
import app.fastorder.fastorder.waiters.infrastructure.dependency_injection.WaiterModuleDependencyContainer
import app.fastorder.fastorder.drinks.infrastructure.dependency_injection.DrinkModuleDependencyContainer
import app.fastorder.fastorder.food.infrastructure.dependency_injection.FoodModuleDependencyContainer
import app.fastorder.fastorder.order.infrastructure.dependency_injection.OrderModuleDependencyContainer

final class EntryPointDependencyContainer(
  waiterDependencies: WaiterModuleDependencyContainer,
  drinkDependencies: DrinkModuleDependencyContainer,
  foodDependencies: FoodModuleDependencyContainer,
  orderDependencies: OrderModuleDependencyContainer
) {
  val statusGetController = new StatusGetController

  val waiterGetController  = new WaiterGetController(waiterDependencies.waiterSearcher)
  val waiterPostController = new WaiterPostController(waiterDependencies.waiterRegister)

  val drinkGetController  = new DrinksGetController(drinkDependencies.drinkSearcher)
  val drinkPostController = new DrinksPostController(drinkDependencies.drinkCreator)

  val foodGetController  = new FoodGetController(foodDependencies.foodSearcher)
  val foodPostController = new FoodPostController(foodDependencies.foodCreator)

  val orderGetController  = new OrderGetController(orderDependencies.orderSearcher)
  val orderPostController = new OrderPostController(orderDependencies.orderCreator)
}
