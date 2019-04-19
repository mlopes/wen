package com.mlopes.wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class YearSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "Year" should {

    "be created with a positive number" in {
      val year = Year(2019, BC)
      year.get.year.value should ===(2019)
      year.get.epoch should ===(BC)
    }

    "fail to be created with a negative number" in {
      Year(-2019, AC) should ===(None)
    }

    "fail to be created with a zero" in {
      Year(0, AC) should ===(None)
    }

    "be pattern matched" in {
      Year(2019, AC).get match {
        case Year(y, e) =>
          y.value should ===(2019)
          e should ===(AC)
        case _ => fail()
      }
    }
  }
}
