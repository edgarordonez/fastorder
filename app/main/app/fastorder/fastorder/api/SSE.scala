package app.fastorder.fastorder.api

import java.time.LocalTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import scala.concurrent.duration._
import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.sse.ServerSentEvent
import akka.http.scaladsl.server.Route

final class SSE {
  private val events = {
    import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling._

    path("events") {
      get {
        complete {
          Source
            .tick(10.seconds, 10.seconds, NotUsed)
            .map(_ => LocalTime.now())
            .map(time => ServerSentEvent(ISO_LOCAL_TIME.format(time)))
            .keepAlive(1.second, () => ServerSentEvent.heartbeat)
        }
      }
    }
  }

  val all: Route = events
}
