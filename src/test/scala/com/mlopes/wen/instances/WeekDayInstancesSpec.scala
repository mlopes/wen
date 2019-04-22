package com.mlopes.wen.instances

import cats._
import com.mlopes.wen.instances.WeekDayInstances._
import com.mlopes.wen.types._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class WeekDayInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "WeekDay Instances" should {

    "provide order" in {
      (Order[WeekDay].compare(Sunday, Monday) < 0) should  ===(true)
      (Order[WeekDay].compare(Wednesday, Monday) > 0) should  ===(true)
      (Order[WeekDay].compare(Tuesday, Tuesday) ===(0)) should  ===(true)
    }

    "provide eq" in {
      Eq[WeekDay].eqv(Friday, Friday) should ===(true)
      Eq[WeekDay].neqv(Saturday, Friday) should ===(true)
    }

    "provide show" in {
      Show[WeekDay].show(Sunday) should ===("Sunday")
      Show[WeekDay].show(Monday) should ===("Monday")
      Show[WeekDay].show(Tuesday) should ===("Tuesday")
      Show[WeekDay].show(Wednesday) should ===("Wednesday")
      Show[WeekDay].show(Thursday) should ===("Thursday")
      Show[WeekDay].show(Friday) should ===("Friday")
      Show[WeekDay].show(Saturday) should ===("Saturday")
    }

  }
}
