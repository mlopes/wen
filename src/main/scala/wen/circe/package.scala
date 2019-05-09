package wen

import java.time.{LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime}

import cats.implicits._
import wen.instances.iso._
import wen.instances.MonthInstances.monthShowInstance
import wen.instances.WeekDayInstances.weekdayShowInstance
import wen.instances.EpochInstances.epochShowInstance
import io.circe.{Decoder, Encoder, Json}
import wen.datetime._
import wen.types._

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

  implicit val zoneTimeEncoder: Encoder[ZoneTime] = new Encoder[ZoneTime] {
    override def apply(a: ZoneTime): Json = Json.fromString(a.show)
  }

  implicit val zoneDateTimeEncoder: Encoder[ZoneDateTime] = new Encoder[ZoneDateTime] {
    override def apply(a: ZoneDateTime): Json = Json.fromString(a.show)
  }

  implicit val hourEncoder: Encoder[Hour] = Encoder.encodeInt.contramap(_.hour.value)

  implicit val minuteEncoder: Encoder[Minute] = Encoder.encodeInt.contramap(_.minute.value)

  implicit val secondEncoder: Encoder[Second] = Encoder.encodeInt.contramap(_.second.value)

  implicit val millisecondEncoder: Encoder[Millisecond] = Encoder.encodeInt.contramap(_.millisecond.value)

  implicit val dayEncoder: Encoder[Day] = Encoder.encodeInt.contramap(_.day.value)

  implicit val monthEncoder: Encoder[Month] = Encoder.encodeString.contramap(_.show)

  implicit val yearEncoder: Encoder[Year] = Encoder.encodeInt.contramap(_.year.value)

  implicit val epochEncoder: Encoder[Epoch] = Encoder.encodeString.contramap(_.show)

  implicit val weekDayEncoder: Encoder[WeekDay] = Encoder.encodeString.contramap(_.show)

  implicit val timeDecoder: Decoder[Time] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(Time(LocalTime.parse(str))).leftMap(t => s"Unable to parse Time ${str} with ${t}")
  }

  implicit val dateDecoder: Decoder[Date] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(Date(LocalDate.parse(str))).leftMap(t => s"Unable to parse Date ${str} with ${t}")
  }

  implicit val dateTimeDecoder: Decoder[DateTime] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(DateTime(LocalDateTime.parse(str))).leftMap(t => s"Unable to parse DateTime ${str} with ${t}")
  }

  implicit val zoneTimeDecoder: Decoder[ZoneTime] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(ZoneTime(OffsetTime.parse(str))).leftMap(t => s"Unable to parse ZoneTime ${str} with ${t}")
  }

  implicit val zoneDateTimeDecoder: Decoder[ZoneDateTime] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(ZoneDateTime(OffsetDateTime.parse(str))).leftMap(t => s"Unable to parse ZoneDateTime ${str} with ${t}")
  }

  implicit val hourDecoder: Decoder[Hour] = Decoder.decodeInt.emap { int =>
    Either.catchNonFatal(Hour.fromInt(int).get).leftMap(t => s"Unable to parse Hour ${int} with ${t}")
  }

  implicit val minuteDecoder: Decoder[Minute] = Decoder.decodeInt.emap { int =>
    Either.catchNonFatal(Minute(int).get).leftMap(t => s"Unable to parse Minute ${int} with ${t}")
  }

  implicit val secondDecoder: Decoder[Second] = Decoder.decodeInt.emap { int =>
    Either.catchNonFatal(Second(int).get).leftMap(t => s"Unable to parse Second ${int} with ${t}")
  }

  implicit val millisecondDecoder: Decoder[Millisecond] = Decoder.decodeInt.emap { int =>
    Either.catchNonFatal(Millisecond(int).get).leftMap(t => s"Unable to parse Millisecond ${int} with ${t}")
  }

  implicit val dayDecoder: Decoder[Day] = Decoder.decodeInt.emap { int =>
    Either.catchNonFatal(Day.fromInt(int).get).leftMap(t => s"Unable to parse Day ${int} with ${t}")
  }

  implicit val monthDecoder: Decoder[Month] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(Month.fromString(str).get).leftMap(t => s"Unable to parse Month ${str} with ${t}")
  }

  implicit val yearDecoder: Decoder[Year] = Decoder.decodeInt.emap { int =>
    Either.catchNonFatal(Year(int).get).leftMap(t => s"Unable to parse Year ${int} with ${t}")
  }

  implicit val epochDecoder: Decoder[Epoch] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(Epoch.fromString(str).get).leftMap(t => s"Unable to parse Epoch ${str} with ${t}")
  }

  implicit val weekdayDecoder: Decoder[WeekDay] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(WeekDay.fromString(str).get).leftMap(t => s"Unable to parse WeekDay ${str} with ${t}")
  }
}
