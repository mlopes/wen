package com.mlopes.wen.types

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

class MonthSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "MonthSpec" should {

    "create a value for January from a number" in {
      Month(1) should ===(Some(January))
    }

    "create a value for February from a number" in {
      Month(2) should ===(Some(February))
    }

    "create a value for March from a number" in {
      Month(3) should ===(Some(March))
    }
    "create a value for April from a number" in {
      Month(4) should ===(Some(April))
    }
    "create a value for May from a number" in {
      Month(5) should ===(Some(May))
    }
    "create a value for June from a number" in {
      Month(6) should ===(Some(June))
    }
    "create a value for July from a number" in {
      Month(7) should ===(Some(July))
    }
    "create a value for August from a number" in {
      Month(8) should ===(Some(August))
    }
    "create a value for September from a number" in {
      Month(9) should ===(Some(September))
    }
    "create a value for October from a number" in {
      Month(10) should ===(Some(October))
    }
    "create a value for November from a number" in {
      Month(11) should ===(Some(November))
    }
    "create a value for December from a number" in {
      Month(12) should ===(Some(December))
    }
    "fail to create a value from a number that doesn't correspond to a month" in {
      Month(13) should ===(None)
    }
  }
}
