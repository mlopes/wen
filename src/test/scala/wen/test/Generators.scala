package wen.test

import org.scalacheck.{Arbitrary, Gen}
import wen.types.{AD, BC, Day, December, Hour, January, Millisecond, Minute, Second, Year}

object Generators {
  lazy val negativeLeapYears: List[Int] = (-2 to -100000 by -2).toList.filter(x => (x % 4 == 0 && x % 100 != 0) || x % 400 == 0)

  val failedYearGen: Gen[Option[Year]] =
    for {
      y <- Gen.negNum[Int]
      e <- Gen.oneOf(BC, AD)
    } yield Year.fromInt(y, e)

  val failedDayGen: Gen[Option[Day]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Day.min || x > Day.max)).map(Day.fromInt(_))

  val failedHourGen: Gen[Option[Hour]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Hour.min || x > Hour.max)).map(Hour(_))

  val failedMinuteGen: Gen[Option[Minute]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Minute.min || x > Minute.max)).map(Minute(_))

  val failedSecondGen: Gen[Option[Second]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Second.min || x > Second.max)).map(Second(_))

  val failedMillisecondGen: Gen[Option[Millisecond]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Millisecond.min || x > Millisecond.max)).map(Millisecond(_))

  val yearAsIntGen: Gen[Int] =
    Gen.posNum[Int]

  val invalidYearAsIntGen: Gen[Int] =
    Gen.negNum[Int]

  val negativeLeapYearAsIntGen: Gen[Int] =
    Gen.oneOf(negativeLeapYears)

  val monthAsIntGen: Gen[Int] =
    Gen.choose(January.asInt, December.asInt)

  val invalidMonthAsIntGen: Gen[Int] =
    Arbitrary.arbitrary[Int] suchThat (x => x < January.asInt || x > December.asInt)

  val dayAsIntGen: Gen[Int] =
    Gen.choose(Day.min, Day.max)

  val invalidDayAsIntGen: Gen[Int] =
    Arbitrary.arbitrary[Int] suchThat (x => x < Day.min || x > Day.max)

  val hourAsIntGen: Gen[Int] =
    Gen.choose(Hour.min, Hour.max)

  val invalidHourAsIntGen: Gen[Int] =
    Arbitrary.arbitrary[Int] suchThat (x => x < Hour.min || x > Hour.max)

  val minuteAsIntGen: Gen[Int] =
    Gen.choose(Minute.min, Minute.max)

  val invalidMinuteAsIntGen: Gen[Int] =
    Arbitrary.arbitrary[Int] suchThat (x => x < Minute.min || x > Minute.max)

  val secondAsIntGen: Gen[Int] =
    Gen.choose(Second.min, Second.max)

  val invalidSecondAsIntGen: Gen[Int] =
    Arbitrary.arbitrary[Int] suchThat (x => x < Second.min || x > Second.max)

  val millisecondAsIntGen: Gen[Int] =
    Gen.choose(Millisecond.min, Millisecond.max)

  val invalidMillisecondAsIntGen: Gen[Int] =
    Arbitrary.arbitrary[Int] suchThat (x => x < Millisecond.min || x > Millisecond.max)

  val optionYearWithDefaultEpochGen: Gen[Option[Year]] =
    Gen.posNum[Int].map(Year(_))

}
