package app.fastorder.fastorder.api.controller.order

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.Created
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import app.fastorder.fastorder.order.application.create.OrderCreator
import app.fastorder.fastorder.order.domain.{OrderDrink, OrderFood, OrderId}
import app.fastorder.fastorder.shared.domain.waiter.WaiterId

final class OrderPostController(creator: OrderCreator) {
  def post(
    id: String,
    waiterId: String,
    table: Int,
    drinks: Seq[OrderDrink],
    food: Seq[OrderFood],
    amount: Double
  ): StandardRoute = {
    creator.create(OrderId(id), WaiterId(waiterId), table, drinks, food, amount)

    complete(HttpResponse(Created))
  }
}
