package wen.instances

import cats.Eq
import cats.implicits._
import wen.instances.DayInstances._
import wen.types.Day
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class DayInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Day Instances" should {
    "provide order" in {
      val day1 = Day(24).get
      val day2 = Day(8).get
      val day3 = Day(31).get
      (day1 compare day1) should ===(0)
      (day1 compare day2) > 0 should ===(true)
      (day2 compare day3) < 0 should ===(true)
    }

    "provide eq" in {
      val day1 = Day(10).get
      val day2 = Day(26).get
      val day3 = Day(30).get

      Eq[Day].eqv(day1, day1) should ===(true)
      Eq[Day].neqv(day1, day2) should ===(true)
      Eq[Day].neqv(day2, day3) should ===(true)
    }

    "provide show" in {
      val day1 = Day(8).get
      val day2 = Day(18).get

      day1.show should ===("8")
      day2.show should ===("18")
    }
  }
}
