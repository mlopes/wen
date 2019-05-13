package wen.instances.datetime

import cats.Eq
import cats.implicits._
import wen.datetime.Offset.{UTCMinus, UTCPlus}
import wen.instances.datetime.TimeInstances._
import wen.datetime.{Offset, Time, ZoneTime}
import wen.types._
import eu.timepit.refined.auto._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class TimeInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  import TimeInstancesSpec._

  "Time Instances" should {
    "provide order for Time" in {
      (time1 compare time2) < 0 should ===(true)
      (time3 compare time2) > 0 should ===(true)
      (time1 compare time1) == 0 should ===(true)
    }

    "provide eq for Time" in {
      Eq[Time].eqv(time1, time1) should ===(true)
      Eq[Time].neqv(time1, time2) should ===(true)
    }

    "provide show for Time" in {
      time1.show should ===("08:53:23.900")
    }

    "provide order for ZoneTime" in {
      (zoneTime1 compare zoneTime2) should ===(0)
      (zoneTime1 compare zoneTime3) should ===(0)
      (zoneTime2 compare zoneTime3) should ===(0)
      (zoneTime2 compare zoneTime4) > 0 should ===(true)
      (zoneTime1 compare zoneTime4) > 0 should ===(true)
      (zoneTime9 compare zoneTime8) < 0 should ===(true)
      (zoneTime6 compare zoneTime1) > 0 should ===(true)

    }

    "provide eq for ZoneTime" in {
      Eq[ZoneTime].eqv(zoneTime1, zoneTime2) should ===(true)
      Eq[ZoneTime].neqv(zoneTime1, zoneTime5) should ===(true)
    }

    "provide show for ZoneTime" in {
      zoneTime7.show should ===("08:15:02.33 +00:00")
      zoneTime8.show should ===("10:31:23.606 -01:00")
      zoneTime9.show should ===("07:53:23.900 +00:30")
    }

    "provide order for Offset" in {
      (offset1 compare offset2) should ===(0)
      (offset1 compare offset3) should ===(0)
      ((offset4 compare offset5) > 0)  should ===(true)
      ((offset5 compare offset4) < 0)  should ===(true)
      ((offset5 compare offset7) > 0)  should ===(true)
      ((offset6 compare offset7) > 0)  should ===(true)
    }

    "provide show for Offset" in {
      offset1.show should ===("+00:00")
      offset2.show should ===("+00:00")
      offset3.show should ===("+00:00")
      offset4.show should ===("+02:10")
      offset5.show should ===("-10:02")
    }
  }
}

object TimeInstancesSpec {
  val time1 = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
  val time2 = Time(Hour(12), Minute(34), Second(12), Millisecond(800))
  val time3 = Time(Hour(23), Minute(59), Second(50), Millisecond(100))

  val offset1 = Offset.UTC
  val offset2 = Offset(UTCPlus, Hour(0), Minute(0))
  val offset3 = Offset(UTCMinus, Hour(0), Minute(0))
  val offset4 = Offset(UTCPlus, Hour(2), Minute(10))
  val offset5 = Offset(UTCMinus, Hour(10), Minute(2))
  val offset6 = Offset(UTCPlus, Hour(10), Minute(2))
  val offset7 = Offset(UTCMinus, Hour(12), Minute(2))

  val zoneTime1 = ZoneTime(Time(Hour(8), Minute(53), Second(23), Millisecond(900)), Offset.UTC)
  val zoneTime2 = ZoneTime(Time(Hour(9), Minute(53), Second(23), Millisecond(900)), Offset(UTCMinus, Hour(1), Minute(0)))
  val zoneTime3 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)), Offset(UTCPlus, Hour(1), Minute(0)))
  val zoneTime4 = ZoneTime(Time(Hour(10), Minute(53), Second(23), Millisecond(900)), Offset(UTCMinus, Hour(3), Minute(0)))
  val zoneTime5 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)), Offset.UTC)
  val zoneTime6 = ZoneTime(Time(Hour(8), Minute(50), Second(23), Millisecond(900)), Offset(UTCPlus, Hour(0), Minute(15)))
  val zoneTime7 = ZoneTime(Time(Hour(8), Minute(15), Second(2), Millisecond(33)), Offset.UTC)
  val zoneTime8 = ZoneTime(Time(Hour(10), Minute(31), Second(23), Millisecond(606)), Offset(UTCMinus, Hour(1), Minute(0)))
  val zoneTime9 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)), Offset(UTCPlus, Hour(0), Minute(30)))
}
