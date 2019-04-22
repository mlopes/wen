package com.mlopes.wen.instances.datetime

import cats._
import com.mlopes.wen.instances.datetime.DateInstances._
import com.mlopes.wen.datetime.Date
import com.mlopes.wen.types._
import eu.timepit.refined.auto._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class DateInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Date Instances" should {

    "provide order" in {
      val date1 = Date.unsafe(Day(1), January, Year(2131, BC))
      val date2 = Date.unsafe(Day(25), July, Year(1975, AD))
      val date3 = Date.unsafe(Day(24), July, Year(2019, AD))
      val date4 = Date.unsafe(Day(25), July, Year(2019, AD))
      val date5 = Date.unsafe(Day(25), December, Year(2019, AD))

      (Order[Date].compare(date1, date2) < 0) ===(true)
      (Order[Date].compare(date2, date1) > 0) ===(true)
      (Order[Date].compare(date2, date3) < 0) ===(true)
      (Order[Date].compare(date3, date4) < 0) ===(true)
      (Order[Date].compare(date4, date5) < 0) ===(true)
      (Order[Date].compare(date2, date2) ===(0)) ===(true)
    }

    "provide eq" in {
      val date1 = Date.unsafe(Day(1), January, Year(2131, BC))
      val date2 = Date.unsafe(Day(25), July, Year(1975, AD))

      Eq[Date].eqv(date1, date1) should ===(true)
      Eq[Date].neqv(date1, date2) should ===(true)
    }

    "provide show" in {
      val date1 = Date.unsafe(Day(25), July, Year(1975, AD))
      val date2 = Date.unsafe(Day(1), January, Year(2131, BC))

      Show[Date].show(date1) should ===("25 July 1975 AD")
      Show[Date].show(date2) should ===("1 January 2131 BC")
    }
  }
}
