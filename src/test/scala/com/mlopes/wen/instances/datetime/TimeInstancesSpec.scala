package com.mlopes.wen.instances.datetime

import cats._
import cats.implicits._
import com.mlopes.wen.instances.datetime.TimeInstances._
import com.mlopes.wen.datetime.Time
import com.mlopes.wen.types._
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
  }

}
