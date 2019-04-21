package app.fastorder.shared.infrastructure.doobie

import cats.effect.IO
import doobie.util.transactor.Transactor
import app.fastorder.shared.infrastructure.doobie.contextShift._
import doobie.syntax.ConnectionIOOps

import scala.concurrent.Future

final class DoobieDbConnection(jdbcConfig: JdbcConfig) {
  val transactor = Transactor.fromDriverManager[IO](
    jdbcConfig.driver,
    jdbcConfig.url,
    jdbcConfig.user,
    jdbcConfig.password
  )

  def read[T](query: ConnectionIOOps[T]): Future[T] = query.transact(transactor).unsafeToFuture()
}
