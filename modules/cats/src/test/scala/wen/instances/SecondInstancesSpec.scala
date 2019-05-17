package wen.instances

import cats.Eq
import cats.implicits._
import wen.instances.SecondInstances._
import wen.types.Second
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import eu.timepit.refined.refineMV

class SecondInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Second Instances" should {
    "provide order" in {
      val second1 = Second(refineMV(27))
      val second2 = Second(refineMV(3))
      val second3 = Second(refineMV(48))
      (second1 compare second1) should ===(0)
      (second1 compare second2) > 0 should ===(true)
      (second2 compare second3) < 0 should ===(true)
    }

    "provide eq" in {
      val second1 = Second(refineMV(8))
      val second2 = Second(refineMV(43))
      val second3 = Second(refineMV(54))

      Eq[Second].eqv(second1, second1) should ===(true)
      Eq[Second].neqv(second1, second2) should ===(true)
      Eq[Second].neqv(second2, second3) should ===(true)
    }

    "provide show" in {
      val second1 = Second(refineMV(3))
      val second2 = Second(refineMV(40))
      val second3 = Second(refineMV(1))

      second1.show should ===("3")
      second2.show should ===("40")
      second3.show should !==("01")
    }
  }

}
