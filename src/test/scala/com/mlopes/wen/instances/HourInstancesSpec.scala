package com.mlopes.wen.instances

import cats._
import com.mlopes.wen.instances.HourInstances._
import com.mlopes.wen.types.Hour
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class HourInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Hour Instances" should {
    "provide order" in {
      val hour1 = Hour(12).get
      val hour2 = Hour(8).get
      val hour3 = Hour(23).get
      (Order[Hour].compare(hour1, hour1) == 0) should ===(true)
      (Order[Hour].compare(hour1, hour2) > 0) should ===(true)
      (Order[Hour].compare(hour2, hour3) < 0) should ===(true)
    }

    "provide eq" in {
      val hour1 = Hour(10).get
      val hour2 = Hour(6).get
      val hour3 = Hour(21).get

      Eq[Hour].eqv(hour1, hour1) should ===(true)
      Eq[Hour].eqv(hour1, hour2) should ===(false)
      Eq[Hour].eqv(hour2, hour3) should ===(false)
    }

    "provide show" in {
      val hour1 = Hour(5).get
      val hour2 = Hour(19).get
      val hour3 = Hour(4).get

      Show[Hour].show(hour1) should ===("05")
      Show[Hour].show(hour2) should ===("19")
      Show[Hour].show(hour3) should !==("4")
    }
  }

}
