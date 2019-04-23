package wen.instances

import cats._
import cats.implicits._
import org.scalatest.{Matchers, WordSpec}
import wen.types._
import wen.instances.MonthInstances._
import org.scalactic.TypeCheckedTripleEquals

class MonthInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Month Instances" should {
    "provide order" in {
      ((January: Month) compare February) < 0 should ===(true)
      ((August: Month) compare May) > 0 should ===(true)
      ((July: Month) compare July) should ===(0)
    }

    "provide eq" in {
      Eq[Month].eqv(December, December) should ===(true)
      Eq[Month].neqv(September, March) should ===(true)
    }

    "provide show" in {
      (January: Month).show should ===("January")
      (February: Month).show should ===("February")
      (March: Month).show should ===("March")
      (April: Month).show should ===("April")
      (May: Month).show should ===("May")
      (June: Month).show should ===("June")
      (July: Month).show should ===("July")
      (August: Month).show should ===("August")
      (September: Month).show should ===("September")
      (October: Month).show should ===("October")
      (November: Month).show should ===("November")
      (December: Month).show should ===("December")
    }
  }
}
