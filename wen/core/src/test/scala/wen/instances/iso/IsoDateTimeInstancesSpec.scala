package wen.instances.iso

import cats.implicits._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import wen.datetime._
import wen.types._
import eu.timepit.refined.auto._

class IsoDateTimeInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "ISO DateTimeInstances" should {
    import IsoDateTimeInstancesSpec._

    "provide ISO show instance for DateTime" in {
      dateTime1.show should ===("1975-07-25T08:53:23")
      dateTime2.show should ===("-2130-01-01T08:53:23")
      dateTime3.show should ===("0000-03-02T08:53:23")
    }

    "provide ISO show instance for ZoneDateTime" in {
      zoneDateTime1.show should ===("1975-07-25T08:53:23Z")
      zoneDateTime2.show should ===("-2130-01-01T08:53:23-01:00")
      zoneDateTime3.show should ===("0000-03-02T08:53:23+02:45")
    }
  }

}

object IsoDateTimeInstancesSpec {
  val date1 = Date.unsafe(Day(25), July, Year(1975, AD))
  val date2 = Date.unsafe(Day(1), January, Year(2131, BC))
  val date3 = Date.unsafe(Day(2), March, Year(1, BC))

  val time = Time(Hour(8), Minute(53), Second(23), Millisecond(900))

  val dateTime1 = DateTime(date1, time)
  val dateTime2 = DateTime(date2, time)
  val dateTime3 = DateTime(date3, time)

  val zoneTime1 = ZoneTime(time, Offset.UTC)
  val zoneTime2 = ZoneTime(time, Offset(Offset.UTCMinus, Hour(1), Minute(0)))
  val zoneTime3 = ZoneTime(time, Offset(Offset.UTCPlus, Hour(2), Minute(45)))

  val zoneDateTime1 = ZoneDateTime(date1, zoneTime1)
  val zoneDateTime2 = ZoneDateTime(date2, zoneTime2)
  val zoneDateTime3 = ZoneDateTime(date3, zoneTime3)

}
