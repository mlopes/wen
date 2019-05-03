package wen.test

import org.scalacheck.{Arbitrary, Gen}
import wen.types.{Day, December, Hour, January, Millisecond, Minute, Second}

object Generators {

  val failedDayGen: Gen[Option[Day]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Day.min || x > Day.max)).map(Day(_))

  val failedHourGen: Gen[Option[Hour]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Hour.min || x > Hour.max)).map(Hour(_))

  val failedMinuteGen: Gen[Option[Minute]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Minute.min || x > Minute.max)).map(Minute(_))

  val failedSecondGen: Gen[Option[Second]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Second.min || x > Second.max)).map(Second(_))

  val failedMillisecondGen: Gen[Option[Millisecond]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Millisecond.min || x > Millisecond.max)).map(Millisecond(_))

  val monthAsIntGen: Gen[Int] =
    Gen.choose(January.asInt, December.asInt)

  val monthDayAsIntGen: Gen[Int] =
    Gen.choose(Day.min, Day.max)

  val hourAsIntGen: Gen[Int] =
    Gen.choose(Hour.min, Hour.max)

  val minuteAsIntGen: Gen[Int] =
    Gen.choose(Minute.min, Minute.max)

  val secondAsIntGen: Gen[Int] =
    Gen.choose(Second.min, Second.max)

  val millisecondAsIntGen: Gen[Int] =
    Gen.choose(Millisecond.min, Millisecond.max)
}
