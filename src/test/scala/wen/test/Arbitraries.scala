package wen.test

import java.time.{LocalDate, LocalTime}

import org.scalacheck.{Arbitrary, Gen}

object Arbitraries {
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
}
