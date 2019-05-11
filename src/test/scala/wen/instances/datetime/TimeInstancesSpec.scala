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
  "Time Instances" should {
    "provide order for Time" in {
      val time1 = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
      val time2 = Time(Hour(12), Minute(34), Second(12), Millisecond(800))
      val time3 = Time(Hour(23), Minute(59), Second(50), Millisecond(100))

      (time1 compare time2) < 0 should ===(true)
      (time3 compare time2) > 0 should ===(true)
      (time1 compare time1) == 0 should ===(true)
    }

    "provide eq for Time" in {
      val time1 = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
      val time2 = Time(Hour(12), Minute(34), Second(12), Millisecond(800))

      Eq[Time].eqv(time1, time1) should ===(true)
      Eq[Time].neqv(time1, time2) should ===(true)
    }

    "provide show for Time" in {
      val time = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
      time.show should ===("08:53:23.900")
    }

    "provide order for ZoneTime" in {
      val time1 = ZoneTime(Time(Hour(8), Minute(53), Second(23), Millisecond(900)),
                           Offset.UTC)
      val time2 = ZoneTime(Time(Hour(9), Minute(53), Second(23), Millisecond(900)),
                           Offset(UTCMinus, Hour(1), Minute(0)))
      val time3 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)),
                           Offset(UTCPlus, Hour(1), Minute(0)))
      val time4 = ZoneTime(Time(Hour(10), Minute(53), Second(23), Millisecond(900)),
                           Offset(UTCMinus, Hour(3), Minute(0)))
      val time5 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)),
                           Offset.UTC)
      val time6 = ZoneTime(Time(Hour(8), Minute(50), Second(23), Millisecond(900)),
                           Offset(UTCPlus, Hour(0), Minute(15)))

      (time1 compare time2) should ===(0)
      (time1 compare time3) should ===(0)
      (time2 compare time3) should ===(0)
      (time2 compare time4) > 0 should ===(true)
      (time1 compare time4) > 0 should ===(true)
      (time5 compare time1) < 0 should ===(true)
      (time6 compare time1) > 0 should ===(true)

    }

    "provide eq for ZoneTime" in {

      val time1 = ZoneTime(Time(Hour(8), Minute(53), Second(23), Millisecond(900)),
                           Offset.UTC)
      val time2 = ZoneTime(Time(Hour(9), Minute(53), Second(23), Millisecond(900)),
                           Offset(UTCMinus, Hour(1), Minute(0)))
      val time3 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)),
                           Offset.UTC)

      Eq[ZoneTime].eqv(time1, time2) should ===(true)
      Eq[ZoneTime].neqv(time1, time3) should ===(true)
    }

    "provide show for ZoneTime" in {
      val time1 = ZoneTime(Time(Hour(8), Minute(15), Second(2), Millisecond(33)),
                           Offset.UTC)
      val time2 = ZoneTime(Time(Hour(10), Minute(31), Second(23), Millisecond(606)),
                           Offset(UTCMinus, Hour(1), Minute(0)))
      val time3 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)),
                           Offset(UTCPlus, Hour(0), Minute(30)))

      time1.show should ===("08:15:02.33 +00:00")
      time2.show should ===("10:31:23.606 -01:00")
      time3.show should ===("07:53:23.900 +00:30")
    }

    "provide order for Offset" in {
      val offset1 = Offset(UTCPlus, Hour(0), Minute(0))
      val offset2 = Offset(UTCMinus, Hour(0), Minute(0))
      val offset3 = Offset(UTCPlus, Hour(2), Minute(10))
      val offset4 = Offset(UTCMinus, Hour(2), Minute(10))
      val offset5 = Offset(UTCPlus, Hour(10), Minute(2))
      val offset6 = Offset(UTCMinus, Hour(10), Minute(2))

      (offset1 compare offset2) should ===(0)
      ((offset3 compare offset4) > 0)  should ===(true)
      ((offset3 compare offset5) < 0)  should ===(true)
      ((offset4 compare offset5) < 0)  should ===(true)
      ((offset4 compare offset6) > 0)  should ===(true)
    }

    "provide show for Offset" in {
      val offset1 = Offset.UTC
      val offset2 = Offset(UTCPlus, Hour(0), Minute(0))
      val offset3 = Offset(UTCMinus, Hour(0), Minute(0))
      val offset4 = Offset(UTCPlus, Hour(2), Minute(10))
      val offset5 = Offset(UTCMinus, Hour(10), Minute(2))

      offset1.show should ===("00:00")
      offset2.show should ===("00:00")
      offset3.show should ===("00:00")
      offset4.show should ===("+02:10")
      offset5.show should ===("-10:02")
    }
  }
}
