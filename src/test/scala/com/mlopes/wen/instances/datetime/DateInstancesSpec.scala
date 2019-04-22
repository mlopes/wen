package com.mlopes.wen.instances.datetime

import cats._
import com.mlopes.wen.instances.datetime.DateInstances._
import com.mlopes.wen.datetime.Date
import com.mlopes.wen.types.{AD, BC, Day, Month, Year}
import eu.timepit.refined.auto._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class DateInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Date Instances" should {

    "provide order" in {
      val date1 = Date(Day(1), Month(1), Year(2131, BC))
      val date2 = Date(Day(25), Month(7), Year(1975, AD))
      val date3 = Date(Day(24), Month(7), Year(2019, AD))
      val date4 = Date(Day(25), Month(7), Year(2019, AD))
      val date5 = Date(Day(25), Month(12), Year(2019, AD))

      (Order[Date].compare(date1, date2) < 0) ===(true)
      (Order[Date].compare(date2, date1) > 0) ===(true)
      (Order[Date].compare(date2, date3) < 0) ===(true)
      (Order[Date].compare(date3, date4) < 0) ===(true)
      (Order[Date].compare(date4, date5) < 0) ===(true)
      (Order[Date].compare(date2, date2) ===(0)) ===(true)
    }

    "provide eq" in {
      val date1 = Date(Day(1), Month(1), Year(2131, BC))
      val date2 = Date(Day(25), Month(7), Year(1975, AD))

      Eq[Date].eqv(date1, date1) should ===(true)
      Eq[Date].neqv(date1, date2) should ===(true)
    }

    "provide show" in {
      val date1 = Date(Day(25), Month(7), Year(1975, AD))
      val date2 = Date(Day(1), Month(1), Year(2131, BC))

      Show[Date].show(date1) should ===("July 25, 1975 AD")
      Show[Date].show(date2) should ===("January 1, 2131 BC")
    }
  }
}
