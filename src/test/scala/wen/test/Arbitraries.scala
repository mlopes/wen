package wen.test

import java.time.{Month => _, Year => _, _}

import org.scalacheck.{Arbitrary, Gen}
import wen.datetime.{Date, DateTime, Offset, Time, ZoneDateTime, ZoneTime}
import wen.types._

object Arbitraries {
  implicit  val optionYearArb: Arbitrary[Option[Year]] = Arbitrary {
    for {
      y <- Gen.posNum[Int]
      e <- Gen.oneOf(BC, AD)
    } yield Year.fromInt(y, e)
  }

  implicit  val optionDayArb: Arbitrary[Option[Day]] = Arbitrary {
    Gen.choose(Day.min, Day.max).map(Day.fromInt(_))
  }

  implicit val dayArb: Arbitrary[Day] = Arbitrary {
    optionDayArb.arbitrary.map(_.get)
  }

  implicit val monthArb: Arbitrary[Month] = Arbitrary {
    Gen.oneOf(January, February, March, April, May, June, July, August, September, October, November, December)
  }

  implicit val yearArb: Arbitrary[Year] = Arbitrary {
    for {
      y <- Gen.posNum[Int]
      e <- Gen.oneOf[Epoch](BC, AD)
    } yield Year.fromInt(y, e).get
  }

  implicit val weekDayArb: Arbitrary[WeekDay] = Arbitrary {
    Gen.oneOf(Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday)
  }

  implicit val optionHourArb: Arbitrary[Option[Hour]] = Arbitrary {
    Gen.choose(Hour.min, Hour.max).map(Hour(_))
  }

  implicit val hourArb: Arbitrary[Hour] = Arbitrary {
    optionHourArb.arbitrary.map(_.get)
  }

  implicit val optionMinuteArb: Arbitrary[Option[Minute]] = Arbitrary {
    Gen.choose(Minute.min, Minute.max).map(Minute(_))
  }

  implicit val minuteArb: Arbitrary[Minute] = Arbitrary {
    optionMinuteArb.arbitrary.map(_.get)
  }

  implicit val optionSecondArb: Arbitrary[Option[Second]] = Arbitrary {
    Gen.choose(Second.min, Second.max).map(Second(_))
  }

  implicit val secondArb: Arbitrary[Second] = Arbitrary {
    optionSecondArb.arbitrary.map(_.get)
  }

  implicit val optionMillisecondArb: Arbitrary[Option[Millisecond]] = Arbitrary {
    Gen.choose(Millisecond.min, Millisecond.max).map(Millisecond(_))
  }

  implicit val millisecondArb: Arbitrary[Millisecond] = Arbitrary {
    optionMillisecondArb.arbitrary.map(_.get)
  }

  implicit val epochArb: Arbitrary[Epoch] = Arbitrary {
    Gen.oneOf(BC, AD)
  }

  implicit val timeArb: Arbitrary[Time] = Arbitrary {
    for {
      h <- hourArb.arbitrary
      m <- minuteArb.arbitrary
      s <- secondArb.arbitrary
      ms <- millisecondArb.arbitrary
    } yield Time(h, m, s, ms)
  }

  implicit val localTimeArb: Arbitrary[LocalTime] = Arbitrary {
    val rangeStart = LocalTime.MIN.toNanoOfDay
    val rangeEnd = LocalTime.MAX.toNanoOfDay
    Gen.choose(rangeStart, rangeEnd).map(i => LocalTime.ofNanoOfDay(i))
  }

  implicit val dateArb: Arbitrary[Date] = Arbitrary {
    for {
      d <- localDateArb.arbitrary
    } yield Date(d)
  }

  implicit val dateTimeArb: Arbitrary[DateTime] = Arbitrary {
    for {
      d <- dateArb.arbitrary
      t <- timeArb.arbitrary
    } yield DateTime(d, t)
  }

  implicit val zoneTimeArb: Arbitrary[ZoneTime] = Arbitrary {
    for {
      t <- timeArb.arbitrary
      o <-  offsetArb.arbitrary
    } yield ZoneTime(t, o)

  }

  implicit val zoneDateTimeArb: Arbitrary[ZoneDateTime] = Arbitrary {
    for {
      odt <- offsetDateTimeArb.arbitrary
    } yield ZoneDateTime(odt)
  }

  implicit val offsetArb: Arbitrary[Offset] = Arbitrary {
    for {
      t <- Gen.oneOf(Offset.UTCMinus, Offset.UTCPlus)
      h <- Gen.choose(0, 17).map(Hour(_))
      m <- minuteArb.arbitrary
    } yield Offset(t, h.get, m)
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
