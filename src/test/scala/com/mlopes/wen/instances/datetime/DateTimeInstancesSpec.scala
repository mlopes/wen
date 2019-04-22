package com.mlopes.wen.instances.datetime

import cats.Eq
import cats.implicits._
import com.mlopes.wen.datetime.{Date, DateTime, Time}
import com.mlopes.wen.instances.datetime.DateTimeInstances._
import com.mlopes.wen.types._
import eu.timepit.refined.auto._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class DateTimeInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals{
  "DateTime Instances" should {
    "provide order" in {
      val date1 = Date.unsafe(Day(12), April, Year(2019, AD))
      val date2 = Date.unsafe(Day(12), May, Year(2019, AD))
      val time1 = Time(Hour(10), Minute(59))
      val time2 = Time(Hour(23), Minute(15))

      val dateTime1: DateTime = DateTime(date1, time1)
      val dateTime2: DateTime = DateTime(date1, time2)
      val dateTime3: DateTime = DateTime(date2, time1)

      (dateTime1 compare dateTime1) should ===(0)
      (dateTime1 compare dateTime2) < 0 should ===(true)
      (dateTime3 compare dateTime2) > 0 should ===(true)
    }

    "provide eq" in {
      val date1 = Date.unsafe(Day(25), April, Year(1974, AD))
      val date2 = Date.unsafe(Day(12), March, Year(2019, AD))
      val time1 = Time(Hour(14), Minute(5))

      val dateTime1: DateTime = DateTime(date1, time1)
      val dateTime2: DateTime = DateTime(date2, time1)

      Eq[DateTime].eqv(dateTime1, dateTime1) should ===(true)
      Eq[DateTime].neqv(dateTime1, dateTime2) should ===(true)
    }

    "provide show" in {
      val date1 = Date.unsafe(Day(2), June, Year(2010, AD))
      val date2 = Date.unsafe(Day(21), December, Year(1519, BC))
      val time1 = Time(Hour(11), Minute(32))

      val dateTime1: DateTime = DateTime(date1, time1)
      val dateTime2: DateTime = DateTime(date2, time1)

      dateTime1.show should ===("2 June 2010 AD 11:32:00.0")
      dateTime2.show should ===("21 December 1519 BC 11:32:00.0")
    }
  }
}
