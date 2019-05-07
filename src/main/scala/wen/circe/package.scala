package wen

import cats.implicits._
import wen.instances.iso._
import io.circe.{Decoder, Encoder, Json}
import java.time._

import wen.datetime._

package object circe {
  implicit val timeEncoder: Encoder[Time] = new Encoder[Time] {
    override def apply(a: Time): Json = Json.fromString(a.show)
  }

  implicit val dateEncoder: Encoder[Date] = new Encoder[Date] {
    override def apply(a: Date): Json = Json.fromString(a.show)
  }

  implicit val dateTimeEncoder: Encoder[DateTime] = new Encoder[DateTime] {
    override def apply(a: DateTime): Json = Json.fromString(a.show)
  }

  implicit val dateZoneTimeEncoder: Encoder[ZoneTime] = new Encoder[ZoneTime] {
    override def apply(a: ZoneTime): Json = Json.fromString(a.show)
  }

  implicit val dateZoneDateTimeEncoder: Encoder[ZoneDateTime] = new Encoder[ZoneDateTime] {
    override def apply(a: ZoneDateTime): Json = Json.fromString(a.show)
  }

  implicit val decodeTimeDecoder: Decoder[Time] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(Time(LocalTime.parse(str))).leftMap(t => s"Unable to parse Time ${str} with ${t}")
  }

  implicit val decodeDateDecoder: Decoder[Date] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(Date(LocalDate.parse(str))).leftMap(t => s"Unable to parse Date ${str} with ${t}")
  }

  implicit val decodeDateTimeDecoder: Decoder[DateTime] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(DateTime(LocalDateTime.parse(str))).leftMap(t => s"Unable to parse DateTime ${str} with ${t}")
  }

  implicit val decodeZoneTimeDecoder: Decoder[ZoneTime] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(ZoneTime(OffsetTime.parse(str))).leftMap(t => s"Unable to parse ZoneTime ${str} with ${t}")
  }

  implicit val decodeZoneDateTimeDecoder: Decoder[ZoneDateTime] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(ZoneDateTime(OffsetDateTime.parse(str))).leftMap(t => s"Unable to parse ZoneDateTime ${str} with ${t}")
  }
}
