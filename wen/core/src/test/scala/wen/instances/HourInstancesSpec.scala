package wen.instances

import cats.Eq
import cats.implicits._
import wen.instances.HourInstances._
import wen.types.Hour
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import eu.timepit.refined.refineMV

class HourInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Hour Instances" should {
    "provide order" in {
      val hour1 = Hour(refineMV(12))
      val hour2 = Hour(refineMV(8))
      val hour3 = Hour(refineMV(23))
      (hour1 compare hour1) should ===(0)
      (hour1 compare hour2) > 0 should ===(true)
      (hour2 compare hour3) < 0 should ===(true)
    }

    "provide eq" in {
      val hour1 = Hour(refineMV(10))
      val hour2 = Hour(refineMV(6))
      val hour3 = Hour(refineMV(21))

      Eq[Hour].eqv(hour1, hour1) should ===(true)
      Eq[Hour].neqv(hour1, hour2) should ===(true)
      Eq[Hour].neqv(hour2, hour3) should ===(true)
    }

    "provide show" in {
      val hour1 = Hour(refineMV(5))
      val hour2 = Hour(refineMV(19))
      val hour3 = Hour(refineMV(4))

      hour1.show should ===("5")
      hour2.show should ===("19")
      hour3.show should !==("04")
    }
  }
}
