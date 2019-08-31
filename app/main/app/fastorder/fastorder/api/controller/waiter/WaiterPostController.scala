package app.fastorder.fastorder.api.controller.waiter

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.Created
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import app.fastorder.fastorder.shared.domain.waiter.WaiterId
import app.fastorder.fastorder.waiters.application.register.WaiterRegister
import app.fastorder.fastorder.waiters.domain.WaiterName

final class WaiterPostController(register: WaiterRegister) {
  def post(id: String, name: String): StandardRoute = {
    register.register(WaiterId(id), WaiterName(name))

    complete(HttpResponse(Created))
  }
}
