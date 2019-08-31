package app.fastorder.fastorder.food.infrastructure.repository

import scala.concurrent.{ExecutionContext, Future}
import doobie.implicits._
import app.fastorder.fastorder.shared.infrastructure.doobie.TypesConversions._
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection
import app.fastorder.fastorder.food.domain.{Food, FoodRepository}

final class DoobieMySqlFoodRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends FoodRepository {

  override def all(): Future[Seq[Food]] =
    db.read(sql"SELECT id, name, price FROM food".query[Food].to[Seq])

  override def save(food: Food): Future[Unit] =
    sql"INSERT INTO food(id, name, price) VALUES (${food.id}, ${food.name}, ${food.price})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
}
