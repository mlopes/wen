package wen.instances.datetime

import cats.Eq
import cats.implicits._
import wen.instances.datetime.DateInstances._
import wen.datetime.Date
import wen.types._
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

      (date1 compare date2) < 0 should ===(true)
      (date2 compare date1) > 0 should ===(true)
      (date2 compare date3) < 0 should ===(true)
      (date3 compare date4) < 0 should ===(true)
      (date4 compare date5) < 0 should ===(true)
      (date2 compare date2) == 0 should ===(true)
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

      date1.show should ===("25 July 1975")
      date2.show should ===("1 January 2131 BC")
    }
  }
}
