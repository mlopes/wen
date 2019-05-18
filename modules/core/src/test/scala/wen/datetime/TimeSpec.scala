package wen.datetime

import java.time.LocalTime

import eu.timepit.refined.auto._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._
import wen.types._

class TimeSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Time" should {
    "be created from hour with everything else defaulting to 0" in forAll { hour: Hour =>
      val time = Time(hour)
      time should ===(Time(hour, Minute(0), Second(0), Millisecond(0)))
    }

    "be created from hour and minute with everything else defaulting to 0" in forAll { (hour: Hour, minute: Minute) =>
      val time = Time(hour, minute)
      time should ===(Time(hour, minute, Second(0), Millisecond(0)))
    }

    "be created from hour, minute and second with milliseocnd defaulting to 0" in { (hour: Hour, minute: Minute, second: Second) =>
      val time = Time(hour, minute, second)
      time should ===(Time(hour, minute, second, Millisecond(0)))
    }

    "be created from a LocalTime" in forAll { localTime: LocalTime =>
      Time(localTime) shouldBe a[Time]
    }
  }
}

