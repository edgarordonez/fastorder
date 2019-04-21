package app.fastorder.fastorder.waiter.application.register

import app.fastorder.fastorder.shared.domain.waiter.WaiterId
import app.fastorder.fastorder.waiter.domain.{Waiter, WaiterName, WaiterRepository}

final class WaiterRegister(repository: WaiterRepository) {
  def register(id: WaiterId, name: WaiterName): Unit = {
    val waiter: Waiter = Waiter(id, name)

    repository.save(waiter)
  }
}
