package wen.instances.datetime

import cats.Eq
import cats.implicits._
import wen.datetime.Offset.{UTCMinus, UTCPlus}
import wen.datetime.{Date, DateTime, Offset, Time, ZoneDateTime, ZoneTime}
import wen.instances.datetime.DateTimeInstances._
import wen.types._
import eu.timepit.refined.auto._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class DateTimeInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals{
  "DateTime Instances" should {
    "provide order for date time" in {
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

    "provide eq for date time" in {
      val date1 = Date.unsafe(Day(25), April, Year(1974, AD))
      val date2 = Date.unsafe(Day(12), March, Year(2019, AD))
      val hour: Hour = Hour(14)
      val time1 = Time(hour)

      val dateTime1: DateTime = DateTime(date1, time1)
      val dateTime2: DateTime = DateTime(date2, time1)

      Eq[DateTime].eqv(dateTime1, dateTime1) should ===(true)
      Eq[DateTime].neqv(dateTime1, dateTime2) should ===(true)
    }

    "provide show for date time" in {
      val date1 = Date.unsafe(Day(2), June, Year(2010, AD))
      val date2 = Date.unsafe(Day(21), December, Year(1519, BC))
      val time1 = Time(Hour(11), Minute(32))

      val dateTime1: DateTime = DateTime(date1, time1)
      val dateTime2: DateTime = DateTime(date2, time1)

      dateTime1.show should ===("2 June 2010 AD 11:32:00.0")
      dateTime2.show should ===("21 December 1519 BC 11:32:00.0")
    }

    "provide order for zone date time" in {
      val date1 = Date.unsafe(Day(12), April, Year(2019, AD))
      val date2 = Date.unsafe(Day(12), May, Year(2019, AD))
      val time1 = ZoneTime(Time(Hour(10), Minute(15)), Offset.UTC)
      val time2 = ZoneTime(Time(Hour(10), Minute(50)), Offset(UTCMinus, Hour(1), Minute(0)))

      val dateTime1: ZoneDateTime = ZoneDateTime(date1, time1)
      val dateTime2: ZoneDateTime = ZoneDateTime(date1, time2)
      val dateTime3: ZoneDateTime = ZoneDateTime(date2, time2)

      (dateTime1 compare dateTime1) should ===(0)
      (dateTime1 compare dateTime2) > 0 should ===(true)
      (dateTime3 compare dateTime2) > 0 should ===(true)
    }

    "provide eq for zone date time" in {
      val date1 = Date.unsafe(Day(22), August, Year(1982, AD))
      val date2 = Date.unsafe(Day(12), August, Year(2016, AD))
      val time1 = ZoneTime(Time(Hour(14), Minute(5)), Offset(UTCPlus, Hour(1), Minute(0)))

      val dateTime1: ZoneDateTime = ZoneDateTime(date1, time1)
      val dateTime2: ZoneDateTime = ZoneDateTime(date2, time1)

      Eq[ZoneDateTime].eqv(dateTime1, dateTime1) should ===(true)
      Eq[ZoneDateTime].neqv(dateTime1, dateTime2) should ===(true)
    }

    "provide show for zone date time" in {
      val date1 = Date.unsafe(Day(11), November, Year(1918, AD))
      val date2 = Date.unsafe(Day(4), December, Year(1919, BC))
      val time1 = ZoneTime(Time(Hour(11), Minute(11), Second(11)), Offset.UTC)
      val time2 = ZoneTime(Time(Hour(12), Minute(10), Second(1), Millisecond(532)),
                           Offset(UTCMinus, Hour(10), Minute(30)))

      val dateTime1: ZoneDateTime = ZoneDateTime(date1, time1)
      val dateTime2: ZoneDateTime = ZoneDateTime(date2, time2)

      dateTime1.show should ===("11 November 1918 AD 11:11:11.0 +00:00")
      dateTime2.show should ===("4 December 1919 BC 12:10:01.532 -10:30")
    }
  }
}
