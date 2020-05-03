package app.fastorder.fastorder.orders.infrastructure.repository

import app.fastorder.fastorder.orders.domain.{Order, OrderRepository}
import app.fastorder.fastorder.shared.infrastructure.doobie.TypesConversions._
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection
import doobie.implicits._
import spray.json._

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlOrderRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends OrderRepository {

  override def all(): Future[Seq[Order]] =
    db.read(sql"SELECT id, waiter_id, dinner_table, drinks, food, amount FROM orders".query[Order].to[Seq])

  override def save(order: Order): Future[Unit] = {
    val orderDrinksToString = (order: Order) => {
      import app.fastorder.fastorder.orders.infrastructure.marshaller.OrderDrinkJsonFormatMarshaller._

      order.drinks.toJson.toString
    }

    val orderFoodToString = (order: Order) => {
      import app.fastorder.fastorder.orders.infrastructure.marshaller.OrderFoodJsonFormatMarshaller._

      order.food.toJson.toString
    }

    sql"INSERT INTO orders(id, waiter_id, dinner_table, drinks, food, amount) VALUES (${order.id}, ${order.waiterId}, ${order.table}, ${orderDrinksToString(
      order
    )}, ${orderFoodToString(order)}, ${order.amount})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
  }
}
