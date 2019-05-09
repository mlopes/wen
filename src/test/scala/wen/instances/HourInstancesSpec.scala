package wen.instances

import cats.Eq
import cats.implicits._
import wen.instances.HourInstances._
import wen.types.Hour
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class HourInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Hour Instances" should {
    "provide order" in {
      val hour1 = Hour.fromInt(12).get
      val hour2 = Hour.fromInt(8).get
      val hour3 = Hour.fromInt(23).get
      (hour1 compare hour1) should ===(0)
      (hour1 compare hour2) > 0 should ===(true)
      (hour2 compare hour3) < 0 should ===(true)
    }

    "provide eq" in {
      val hour1 = Hour.fromInt(10).get
      val hour2 = Hour.fromInt(6).get
      val hour3 = Hour.fromInt(21).get

      Eq[Hour].eqv(hour1, hour1) should ===(true)
      Eq[Hour].neqv(hour1, hour2) should ===(true)
      Eq[Hour].neqv(hour2, hour3) should ===(true)
    }

    "provide show" in {
      val hour1 = Hour.fromInt(5).get
      val hour2 = Hour.fromInt(19).get
      val hour3 = Hour.fromInt(4).get

      hour1.show should ===("5")
      hour2.show should ===("19")
      hour3.show should !==("04")
    }
  }
}
