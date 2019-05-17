package wen.instances

import cats.Eq
import cats.implicits._
import wen.instances.MinuteInstances._
import wen.types.Minute
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import eu.timepit.refined.refineMV

class MinuteInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Minute Instances" should {
    "provide order" in {
      val minute1 = Minute(refineMV(24))
      val minute2 = Minute(refineMV(8))
      val minute3 = Minute(refineMV(59))
      (minute1 compare minute1) should ===(0)
      (minute1 compare minute2) > 0 should ===(true)
      (minute2 compare minute3) < 0 should ===(true)
    }

    "provide eq" in {
      val minute1 = Minute(refineMV(10))
      val minute2 = Minute(refineMV(36))
      val minute3 = Minute(refineMV(41))

      Eq[Minute].eqv(minute1, minute1) should ===(true)
      Eq[Minute].neqv(minute1, minute2) should ===(true)
      Eq[Minute].neqv(minute2, minute3) should ===(true)
    }

    "provide show" in {
      val minute1 = Minute(refineMV(8))
      val minute2 = Minute(refineMV(39))
      val minute3 = Minute(refineMV(4))

      minute1.show should ===("8")
      minute2.show should ===("39")
      minute3.show should !==("04")
    }
  }
}
