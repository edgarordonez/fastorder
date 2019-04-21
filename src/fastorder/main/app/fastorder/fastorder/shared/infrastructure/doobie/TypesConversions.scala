package app.fastorder.fastorder.shared.infrastructure.doobie

import java.util.UUID
import doobie.util.Meta

object TypesConversions {
  implicit val UUIDMeta: Meta[UUID] = Meta[String].imap(UUID.fromString)(_.toString)
}
