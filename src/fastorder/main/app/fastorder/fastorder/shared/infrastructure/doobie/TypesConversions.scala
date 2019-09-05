package app.fastorder.fastorder.shared.infrastructure.doobie

import java.util.UUID

import app.fastorder.fastorder.order.domain.OrderDrink
import app.fastorder.fastorder.order.infrastructure.marshaller.OrderDrinkJsonFormatMarshaller._
import cats.syntax.either._
import doobie.util.Meta
import io.circe.Json
import io.circe.parser._
import org.postgresql.util.PGobject
import spray.json.JsonParser

object TypesConversions {
  implicit val UUIDMeta: Meta[UUID] = Meta[String].imap(UUID.fromString)(_.toString)

  implicit val jsonMeta: Meta[Json] =
    Meta.Advanced
      .other[PGobject]("json")
      .timap[Json](a => parse(a.getValue).leftMap[Json](e => throw e).merge)(
        a => {
          val o = new PGobject
          o.setType("json")
          o.setValue(a.noSpaces)
          o
        }
      )

  implicit val OrderDrinkSeqMeta: Meta[Seq[OrderDrink]] =
    jsonMeta.imap(f => JsonParser(f.toString).convertTo[Seq[OrderDrink]])(
      g => parse(g.toString).leftMap[Json](e => throw e).merge
    )
}
