package app.fastorder.fastorder.waiters.application.search

import scala.concurrent.Future
import app.fastorder.fastorder.waiters.domain.{Waiter, WaiterRepository}

final class WaiterSearcher(repository: WaiterRepository) {
  def all(): Future[Seq[Waiter]] = repository.all()
}
