package wen.circe

import io.circe.Json
import org.scalatest.{Matchers, WordSpec}
import wen.types._
import cats.implicits._
import io.circe.syntax._
import io.circe.literal._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.datetime._
import wen.test.Arbitraries._

class CirceSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {

  "Circe Encoders" should {

    "encode Hour" in forAll { sut: Hour =>
      val expected: Json = json"""${sut.hour.value}"""
      sut.asJson should ===(expected)
    }

    "encode Minute" in forAll { sut: Minute =>
      val expected: Json = json"""${sut.minute.value}"""
      sut.asJson should ===(expected)
    }

    "encode Second" in forAll { sut: Second =>
      val expected: Json = json"""${sut.second.value}"""
      sut.asJson should ===(expected)
    }

    "encode Millisecond" in forAll { sut: Millisecond =>
      val expected: Json = json"""${sut.millisecond.value}"""
      sut.asJson should ===(expected)
    }

    "encode Day" in forAll { sut: Day =>
      val expected: Json = json"""${sut.day.value}"""
      sut.asJson should ===(expected)
    }

    "encode Month" in forAll { sut: Month =>
      import wen.instances.MonthInstances.monthShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode Year" in forAll { sut: Year =>
      val expected: Json = json"""${sut.year.value}"""
      sut.asJson should ===(expected)
    }

    "encode Epoch" in forAll { sut: Epoch =>
      import wen.instances.EpochInstances.epochShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode WeekDay" in forAll { sut: WeekDay =>
      import wen.instances.WeekDayInstances.weekdayShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode Time in ISO format" in forAll { sut: Time =>
      import wen.instances.iso.TimeInstances.isoTimeShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode Date in ISO format" in forAll { sut: Date =>
      import wen.instances.iso.DateInstances.isoDateShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode DateTime in ISO format" in forAll { sut: DateTime =>
      import wen.instances.iso.DateTimeInstances.isoDateTimeShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

  }
  "Circe Decoders" should {
  }
}
