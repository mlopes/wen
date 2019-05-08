package wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class WeekDaySpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "WeekDay" should {

    "be created from String" in {
      WeekDay.fromString("Sunday").get should ===(Sunday)
      WeekDay.fromString("Monday").get should ===(Monday)
      WeekDay.fromString("Tuesday").get should ===(Tuesday)
      WeekDay.fromString("Wednesday").get should ===(Wednesday)
      WeekDay.fromString("Thursday").get should ===(Thursday)
      WeekDay.fromString("Friday").get should ===(Friday)
      WeekDay.fromString("Saturday").get should ===(Saturday)
    }
  }
}
