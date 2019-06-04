package wen.avro4s

import java.time.{Instant, LocalDateTime, LocalTime, ZoneOffset}

import com.sksamuel.avro4s.{AvroSchema, Encoder, ImmutableRecord}
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.datetime.{DateTime, Time}
import wen.test.Generators._

class Avro4sSpec extends WordSpec  with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks  {

  "Avro4s Encoders" should {
    "encode a Time" in forAll (timeOfDayInMilliseconds) { millisecondsOfDay: Int =>
      case class Foo(s: Time)

      val schema = AvroSchema[Foo]
      val localTime = LocalTime.ofNanoOfDay(millisecondsOfDay * 1000000L)
      val time = Time(localTime)

      Encoder[Foo].encode(Foo(time), schema) shouldBe ImmutableRecord(schema, Vector(java.lang.Integer.valueOf(millisecondsOfDay)))
    }

    "encode a DateTime" in forAll (epochLong) { timestamp: Long =>
      case class Foo(s: DateTime)

      val schema = AvroSchema[Foo]
      val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
      val dateTime = DateTime(localDateTime)

      Encoder[Foo].encode(Foo(dateTime), schema) shouldBe ImmutableRecord(schema, Vector(java.lang.Long.valueOf(timestamp)))
    }
  }

}
