package wen.test

import org.scalacheck.{Arbitrary, Gen}
import wen.types.Day

object Generators {
  private val minMonthDay: Int = 1
  private val maxMonthDay: Int = 31

  val failedDayGen: Gen[Option[Day]] =
    (Arbitrary.arbitrary[Int] suchThat (x => x < 1 || x > 31)).map(Day(_))

  val monthDayAsIntGen: Gen[Int] =
    Gen.choose(minMonthDay, maxMonthDay)


}
