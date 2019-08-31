package app.fastorder.fastorder.api.controller.food

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.Created
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import app.fastorder.fastorder.food.application.create.FoodCreator
import app.fastorder.fastorder.food.domain.{FoodId, FoodName}
import app.fastorder.fastorder.shared.domain.price.Price

class FoodPostController(creator: FoodCreator) {
  def post(id: String, name: String, price: Double): StandardRoute = {
    creator.create(FoodId(id), FoodName(name), Price(price))

    complete(HttpResponse(Created))
  }
}
