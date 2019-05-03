package wen.test

import org.scalacheck.{Arbitrary, Gen}
import wen.types.{Day, Hour}

object Generators {

  val failedDayGen: Gen[Option[Day]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Day.min || x > Day.max)).map(Day(_))

  val failedHourGen: Gen[Option[Hour]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < Hour.min || x > Hour.max)).map(Hour(_))

  val monthDayAsIntGen: Gen[Int] =
    Gen.choose(Day.min, Day.max)

  val hourAsIntGen: Gen[Int] =
    Gen.choose(Hour.min, Hour.max)

}
