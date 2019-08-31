package app.fastorder.fastorder.api.controller.drinks

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.Created
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import app.fastorder.fastorder.drinks.application.create.DrinkCreator
import app.fastorder.fastorder.drinks.domain.{DrinkId, DrinkName}
import app.fastorder.fastorder.shared.domain.price.Price

class DrinksPostController(creator: DrinkCreator) {
  def post(id: String, name: String, price: Double): StandardRoute = {
    creator.create(DrinkId(id), DrinkName(name), Price(price))

    complete(HttpResponse(Created))
  }
}
