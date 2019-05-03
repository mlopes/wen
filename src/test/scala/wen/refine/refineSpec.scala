package wen.refine

import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Generators._

class refineSpec extends WordSpec with Matchers with ScalaCheckDrivenPropertyChecks {

  "refineSecond" should {

    "refine Second" in forAll(secondAsIntGen) { secondAsInt: Int =>
      refineSecond(secondAsInt) match {
        case Right(s1) => s1.value should ===(secondAsInt)
        case _ => fail
      }
    }

    "fail to refine non-second" in forAll(invalidSecondAsIntGen) { invalidSecondAsInt: Int =>
      refineSecond(invalidSecondAsInt) match {
        case Left(_) => succeed
        case _ => fail
      }
    }
  }

  "refineMinute" should {

    "refine Minute" in forAll(minuteAsIntGen) { minuteAsInt: Int =>
      refineMinute(minuteAsInt) match {
        case Right(m1) => m1.value should ===(minuteAsInt)
        case _ => fail
      }
    }

    "fail to refine non-minute" in forAll(invalidMinuteAsIntGen) { invalidMinuteAsInt: Int =>
      refineMinute(invalidMinuteAsInt) match {
        case Left(_) => succeed
        case _ => fail
      }
    }
  }

  "refineYear" should {

    "refine Year" in forAll(yearAsIntGen) { yearAsInt: Int =>
      refineYear(yearAsInt) match {
        case Right(y1) => y1.value should ===(yearAsInt)
        case _ => fail
      }
    }

    "fail to refine negative numbers" in forAll(invalidYearAsIntGen) { invalidYearAsInt: Int =>
      refineYear(invalidYearAsInt) match {
        case Left(_) => succeed
        case _ => fail
      }
    }

    "should fail to refine 0" in {
      refineYear(0) match {
        case Left(_) => succeed
        case _ => fail
      }
    }
  }

  "refineHour" should {

    "refine Hour" in forAll(hourAsIntGen) { hourAsInt: Int =>
      refineHour(hourAsInt) match {
        case Right(h1) => h1.value should ===(hourAsInt)
        case _ => fail
      }
    }

    "fail to refine non-hour" in forAll(invalidHourAsIntGen) { invalidHourAsInt: Int =>
      refineHour(invalidHourAsInt) match {
        case Left(_) => succeed
        case _ => fail
      }
    }
  }

  "refineMillisecond" should {

    "refine Millisecond" in forAll(millisecondAsIntGen) { millisecondAsInt: Int =>
      refineMillisecond(millisecondAsInt) match {
        case Right(m1) => m1.value should ===(millisecondAsInt)
        case _ => fail
      }
    }

    "fail to refine non-millisecond" in forAll(invalidMillisecondAsIntGen) { invalidMillisecondAsInt: Int =>
      refineMillisecond(invalidMillisecondAsInt) match {
        case Left(_) => succeed
        case _ => fail
      }
    }
  }

  "refineDay" should {

    "refine Day" in forAll(dayAsIntGen) { dayAsInt: Int =>
      refineDay(dayAsInt) match {
        case Right(d1) => d1.value should ===(dayAsInt)
        case _ => fail
      }
    }

    "fail to refine non-day" in forAll(invalidDayAsIntGen) { invalidDayAsInt: Int =>
      refineMonth(invalidDayAsInt) match {
        case Left(_) => succeed
        case _ => fail
      }
    }
  }

  "refineMonth" should {
    "refine Month" in forAll(monthAsIntGen) { monthAsInt: Int =>
      refineMonth(monthAsInt) match {
        case Right(m1) => m1.value should  ===(monthAsInt)
        case _ => fail
      }
    }

    "fail to refine non-month" in forAll(invalidMonthAsIntGen) { invalidMonthAsInt: Int =>
      refineMonth(invalidMonthAsInt) match {
        case Left(_) => succeed
        case _ => fail
      }
    }
  }
}
