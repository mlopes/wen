package wen.instances

import cats.Eq
import cats.implicits._
import wen.instances.MillisecondInstances._
import wen.types.Millisecond
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import eu.timepit.refined.refineMV

class MillisecondInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Millisecond Instances" should {
    "provide order" in {
      val millisecond1 = Millisecond(refineMV(257))
      val millisecond2 = Millisecond(refineMV(36))
      val millisecond3 = Millisecond(refineMV(481))
      (millisecond1 compare millisecond1) should ===(0)
      (millisecond1 compare millisecond2) > 0 should ===(true)
      (millisecond2 compare millisecond3) < 0 should ===(true)
    }

    "provide eq" in {
      val millisecond1 = Millisecond(refineMV(8))
      val millisecond2 = Millisecond(refineMV(143))
      val millisecond3 = Millisecond(refineMV(540))

      Eq[Millisecond].eqv(millisecond1, millisecond1) should ===(true)
      Eq[Millisecond].neqv(millisecond1, millisecond2) should ===(true)
      Eq[Millisecond].neqv(millisecond2, millisecond3) should ===(true)
    }

    "provide show" in {
      val millisecond1 = Millisecond(refineMV(2))
      val millisecond2 = Millisecond(refineMV(999))

      millisecond1.show should ===("2")
      millisecond2.show should ===("999")
    }
  }
}
