package com.mlopes.wen.instances

import cats._
import com.mlopes.wen.instances.DayInstances._
import com.mlopes.wen.types.Day
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class DayInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Day Instances" should {
    "provide order" in {
      val day1 = Day(24).get
      val day2 = Day(8).get
      val day3 = Day(31).get
      (Order[Day].compare(day1, day1) == 0) should ===(true)
      (Order[Day].compare(day1, day2) > 0) should ===(true)
      (Order[Day].compare(day2, day3) < 0) should ===(true)
    }

    "provide eq" in {
      val day1 = Day(10).get
      val day2 = Day(26).get
      val day3 = Day(30).get

      Eq[Day].eqv(day1, day1) should ===(true)
      Eq[Day].eqv(day1, day2) should ===(false)
      Eq[Day].eqv(day2, day3) should ===(false)
    }

    "provide show" in {
      val day1 = Day(8).get
      val day2 = Day(18).get

      Show[Day].show(day1) should ===("8")
      Show[Day].show(day2) should ===("18")
    }
  }

}
