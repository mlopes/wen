package wen.circe

import cats.Eq
import io.circe.{DecodingFailure, Json}
import org.scalatest.{Matchers, WordSpec}
import wen.types._
import cats.implicits._
import io.circe.syntax._
import io.circe.literal._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.datetime._
import wen.test.Arbitraries._
import wen.test.Generators._

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
      import wen.instances.iso.isoTimeShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode Date in ISO format" in forAll { sut: Date =>
      import wen.instances.iso.isoDateShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode DateTime in ISO format" in forAll { sut: DateTime =>
      import wen.instances.iso.isoDateTimeShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode ZoneTime in ISO format" in forAll { sut: ZoneTime =>
      import wen.instances.iso.isoZoneTimeShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

    "encode ZoneDateTime in ISO format" in forAll { sut: ZoneDateTime =>
      import wen.instances.iso.isoZoneDateTimeShowInstance

      val expected: Json = json"""${sut.show}"""
      sut.asJson should ===(expected)
    }

  }

  "Circe Decoders" should {
    "decode Hour" in forAll(hourAsIntGen) { hourAsInt: Int =>
      val json: Json = json"""${hourAsInt}"""
      json.as[Hour].toOption should ===(Hour.fromInt(hourAsInt))
    }

    "fail to decode invalid Hour" in forAll(invalidHourAsIntGen) { invalidHourAsInt: Int =>
      val json: Json = json"""${invalidHourAsInt}"""
      json.as[Hour] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Minute" in forAll(minuteAsIntGen) { minuteAsInt: Int =>
      val json: Json = json"""${minuteAsInt}"""
      json.as[Minute].toOption should ===(Minute.fromInt(minuteAsInt))
    }

    "fail to decode invalid Minute" in forAll(invalidMinuteAsIntGen) { invalidMinuteAsInt: Int =>
      val json: Json = json"""${invalidMinuteAsInt}"""
      json.as[Minute] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Second" in forAll(secondAsIntGen) { secondAsInt: Int =>
      val json: Json = json"""${secondAsInt}"""
      json.as[Second].toOption should ===(Second.fromInt(secondAsInt))
    }

    "fail to decode invalid Second" in forAll(invalidSecondAsIntGen) { invalidSecondAsInt: Int =>
      val json: Json = json"""${invalidSecondAsInt}"""
      json.as[Second] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Millisecond" in forAll(millisecondAsIntGen) { millisecondAsInt: Int =>
      val json: Json = json"""${millisecondAsInt}"""
      json.as[Millisecond].toOption should ===(Millisecond.fromInt(millisecondAsInt))
    }

    "fail to decode invalid Millisecond" in forAll(invalidMillisecondAsIntGen) { invalidMillisecondAsInt: Int =>
      val json: Json = json"""${invalidMillisecondAsInt}"""
      json.as[Millisecond] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Day" in forAll(dayAsIntGen) { dayAsInt: Int =>
      val json: Json = json"""${dayAsInt}"""
      json.as[Day].toOption should ===(Day.fromInt(dayAsInt))
    }

    "fail to decode invalid Day" in forAll(invalidDayAsIntGen) { invalidDayAsInt: Int =>
      val json: Json = json"""${invalidDayAsInt}"""
      json.as[Day] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Month" in forAll { month: Month =>
      import wen.instances.MonthInstances.monthShowInstance

      val json: Json = json"""${month.show}"""
      json.as[Month].toOption should ===(Some(month))
    }

    "fail to decode invalid Month" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[Month] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Year" in forAll(yearAsIntGen) { yearAsInt: Int =>
      val json: Json = json"""${yearAsInt}"""
      json.as[Year].toOption should ===(Year.fromInt(yearAsInt))
    }

    "fail to decode invalid Year" in forAll(invalidYearAsIntGen) { invalidYearAsInt: Int =>
      val json: Json = json"""${invalidYearAsInt}"""
      json.as[Year] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Epoch" in forAll { epoch: Epoch =>
      import wen.instances.EpochInstances.epochShowInstance

      val json: Json = json"""${epoch.show}"""
      json.as[Epoch].toOption should ===(Some(epoch))
    }

    "fail to decode invalid Epoch" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[Epoch] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode WeekDay" in forAll { weekDay: WeekDay =>
      import wen.instances.WeekDayInstances.weekdayShowInstance

      val json: Json = json"""${weekDay.show}"""
      json.as[WeekDay].toOption should ===(Some(weekDay))
    }

    "fail to decode invalid WeekDay" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[WeekDay] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Time in ISO format" in forAll { time: Time =>
      import wen.instances.iso.isoTimeShowInstance

      val json: Json = json"""${time.show}"""
      json.as[Time].toOption should ===(Some(Time(time.hour, time.minute, time.second)))
    }

    "fail to decode invalid Time" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[Time] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode Date in ISO format" in forAll { date: Date =>
      import wen.instances.iso.isoDateShowInstance

      val json: Json = json"""${date.show}"""
      json.as[Date].toOption should ===(Some(date))
    }

    "fail to decode invalid Date" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[Date] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode DateTime in ISO format" in forAll { dateTime: DateTime =>
      import wen.instances.iso.isoDateTimeShowInstance

      val json: Json = json"""${dateTime.show}"""
      val time = Time(dateTime.time.hour, dateTime.time.minute, dateTime.time.second)

      json.as[DateTime].toOption should ===(Some(DateTime(dateTime.date, time)))
    }

    "fail to decode invalid DateTime" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[DateTime] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode ZoneTime in ISO format" in forAll { zoneTime: ZoneTime =>
      import wen.instances.iso.isoZoneTimeShowInstance
      import wen.instances.datetime.TimeInstances.zoneTimeOrderInstance

      val json: Json = json"""${zoneTime.show}"""
      val time = Time(zoneTime.time.hour, zoneTime.time.minute, zoneTime.time.second)

      Eq[ZoneTime].eqv(json.as[ZoneTime].toOption.get,
                       ZoneTime(time, zoneTime.offset)) should ===(true)
    }

    "fail to decode invalid ZoneTime" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[ZoneTime] shouldBe a[Left[DecodingFailure, _]]
    }

    "decode ZoneDateTime in ISO format" in forAll { zoneDateTime: ZoneDateTime =>
      import wen.instances.iso.isoZoneDateTimeShowInstance
      import wen.instances.datetime.DateTimeInstances.zoneDateTimeOrderInstance

      val json: Json = json"""${zoneDateTime.show}"""

      val date = zoneDateTime.date
      val time = Time(zoneDateTime.zoneTime.time.hour, zoneDateTime.zoneTime.time.minute, zoneDateTime.zoneTime.time.second)
      val zoneTime = ZoneTime(time, zoneDateTime.zoneTime.offset)

      Eq[ZoneDateTime].eqv(json.as[ZoneDateTime].toOption.get,
                                ZoneDateTime(date, zoneTime)) should ===(true)
    }

    "fail to decode invalid ZoneDateTime" in forAll { arbitraryString: String =>
      val json: Json = json"""${arbitraryString}"""
      json.as[ZoneDateTime] shouldBe a[Left[DecodingFailure, _]]
    }
  }
}
