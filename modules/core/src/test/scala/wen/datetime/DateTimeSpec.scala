package wen.datetime

import java.time.LocalDateTime

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.test.Arbitraries._

class DateTimeSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "DateTime" should {
    "be created from java.time.LocalDateTime" in forAll { localDateTime: LocalDateTime =>
      val dateTime = DateTime(localDateTime)
      dateTime shouldBe a[DateTime]
    }
  }
}
