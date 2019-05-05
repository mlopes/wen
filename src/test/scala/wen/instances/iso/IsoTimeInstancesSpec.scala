package wen.instances.iso

import cats.implicits._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import wen.datetime.{Offset, Time, ZoneTime}
import wen.types.{Hour, Millisecond, Minute, Second}
import eu.timepit.refined.auto._
import wen.datetime.Offset.{UTCMinus, UTCPlus}

class IsoTimeInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "ISO TimeInstances" should {

    "provide ISO format show for time" in {
      val time = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
      time.show should ===("08:53:23")
    }

    "provide ISO format show for zone time" in {
      val zoneTime1 = ZoneTime(Time(Hour(8), Minute(15), Second(2), Millisecond(33)),
        Offset.UTC)
      val zoneTime2 = ZoneTime(Time(Hour(10), Minute(31), Second(23), Millisecond(606)),
        Offset(UTCMinus, Hour(1), Minute(0)))
      val zoneTime3 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)),
        Offset(UTCPlus, Hour(0), Minute(30)))

      zoneTime1.show should ===("08:15:02Z")
      zoneTime2.show should ===("10:31:23-01:00")
      zoneTime3.show should ===("07:53:23+00:30")
    }
  }
}
