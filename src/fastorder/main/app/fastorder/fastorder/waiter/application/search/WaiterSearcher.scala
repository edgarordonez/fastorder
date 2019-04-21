package app.fastorder.fastorder.waiter.application.search

import scala.concurrent.Future
import app.fastorder.fastorder.waiter.domain.{Waiter, WaiterRepository}

final class WaiterSearcher(repository: WaiterRepository) {
  def all(): Future[Seq[Waiter]] = repository.all()
}
