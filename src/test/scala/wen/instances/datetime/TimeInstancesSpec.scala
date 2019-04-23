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
    "provide order for time" in {
      val time1 = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
      val time2 = Time(Hour(12), Minute(34), Second(12), Millisecond(800))
      val time3 = Time(Hour(23), Minute(59), Second(50), Millisecond(100))

      (time1 compare time2) < 0 should ===(true)
      (time3 compare time2) > 0 should ===(true)
      (time1 compare time1) == 0 should ===(true)
    }

    "provide eq for time" in {
      val time1 = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
      val time2 = Time(Hour(12), Minute(34), Second(12), Millisecond(800))

      Eq[Time].eqv(time1, time1) should ===(true)
      Eq[Time].neqv(time1, time2) should ===(true)
    }

    "provide show for time" in {
      val time = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
      time.show should ===("08:53:23.900")
    }

    "provide order for zone time" in {
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

    "provide eq for zone time" in {

      val time1 = ZoneTime(Time(Hour(8), Minute(53), Second(23), Millisecond(900)),
                           Offset.UTC)
      val time2 = ZoneTime(Time(Hour(9), Minute(53), Second(23), Millisecond(900)),
                           Offset(UTCMinus, Hour(1), Minute(0)))
      val time3 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)),
                           Offset.UTC)

      Eq[ZoneTime].eqv(time1, time2) should ===(true)
      Eq[ZoneTime].neqv(time1, time3) should ===(true)
    }

    "provide show for zone time" in {
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
  }
}
