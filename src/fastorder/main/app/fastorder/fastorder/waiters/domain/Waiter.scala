package app.fastorder.fastorder.waiters.domain

import app.fastorder.fastorder.shared.domain.waiter.WaiterId

object Waiter {
  def apply(id: String, name: String): Waiter = Waiter(WaiterId(id), WaiterName(name))
}

case class Waiter(id: WaiterId, name: WaiterName)
