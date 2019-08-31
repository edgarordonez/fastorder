package app.fastorder.fastorder.waiters.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import app.fastorder.fastorder.shared.infrastructure.marshaller.waiter.WaiterIdJsonFormatMarshaller._
import app.fastorder.fastorder.waiters.infrastructure.marshaller.WaiterNameJsonFormatMarshaller._
import app.fastorder.fastorder.shared.domain.waiter.WaiterId
import app.fastorder.fastorder.waiters.domain.{Waiter, WaiterName}

object WaiterJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val waiterFormat: RootJsonFormat[Waiter] = jsonFormat2(Waiter.apply(_: WaiterId, _: WaiterName))
}
