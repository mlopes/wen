package wen.test

import java.time._

import org.scalacheck.{Arbitrary, Gen}
import wen.types.{Day, Hour, Millisecond}

object Arbitraries {
  implicit  val optionDayArb: Arbitrary[Option[Day]] = Arbitrary {
    Gen.choose(Day.min, Day.max).map(Day(_))
  }

  implicit val optionHourArb: Arbitrary[Option[Hour]] = Arbitrary {
    Gen.choose(Hour.min, Hour.max).map(Hour(_))
  }

  implicit val optionMillisecondArb: Arbitrary[Option[Millisecond]] = Arbitrary {
    Gen.choose(Millisecond.min, Millisecond.max).map(Millisecond(_))
  }

  implicit val localTimeArb: Arbitrary[LocalTime] = Arbitrary {
    val rangeStart = LocalTime.MIN.toNanoOfDay
    val rangeEnd = LocalTime.MAX.toNanoOfDay
    Gen.choose(rangeStart, rangeEnd).map(i => LocalTime.ofNanoOfDay(i))
  }

  implicit val localDateArb: Arbitrary[LocalDate] = Arbitrary {
    val rangeStart = LocalDate.MIN.toEpochDay
    val rangeEnd = LocalDate.MAX.toEpochDay
    Gen.choose(rangeStart, rangeEnd).map(i => LocalDate.ofEpochDay(i))
  }

  implicit val localDateTimeArb: Arbitrary[LocalDateTime] = Arbitrary {
    val rangeStart = LocalDateTime.MIN.toEpochSecond(ZoneOffset.UTC)
    val rangeEnd = LocalDateTime.MAX.toEpochSecond(ZoneOffset.UTC)
    Gen.choose(rangeStart, rangeEnd).map(i => LocalDateTime.ofEpochSecond(i, 0, ZoneOffset.UTC))
  }

  implicit val offsetTimeArb: Arbitrary[OffsetTime] = Arbitrary {
    val rangeStart = OffsetTime.MIN.toLocalTime.toNanoOfDay
    val rangeEnd = OffsetTime.MAX.toLocalTime.toNanoOfDay
    Gen.choose(rangeStart, rangeEnd).map(i => OffsetTime.of(LocalTime.ofNanoOfDay(i), ZoneOffset.UTC))
  }

  implicit val zoneOffsetArb: Arbitrary[ZoneOffset] = Arbitrary {
    val rangeStart = ZoneOffset.MIN.getTotalSeconds
    val rangeEnd = ZoneOffset.MAX.getTotalSeconds
    Gen.choose(rangeStart, rangeEnd).map(i => ZoneOffset.ofTotalSeconds(i))
  }

  implicit val instantArb: Arbitrary[Instant] = Arbitrary {
    Gen.choose(Long.MinValue, Long.MaxValue).map(i => Instant.ofEpochMilli(i))
  }

  implicit val offsetDateTimeArb: Arbitrary[OffsetDateTime] = Arbitrary {
    val rangeStart = OffsetDateTime.MIN.toEpochSecond
    val rangeEnd = OffsetDateTime.MAX.toEpochSecond
    Gen.choose(rangeStart, rangeEnd).map(i => OffsetDateTime.ofInstant(Instant.ofEpochSecond(i), ZoneOffset.UTC))
  }
}
