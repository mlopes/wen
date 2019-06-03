package wen.avro4s

import java.time.{Instant, LocalDateTime, ZoneOffset}

import com.sksamuel.avro4s.{AvroSchema, Encoder, ImmutableRecord}
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.datetime.DateTime
import wen.test.Generators._

class Avro4sSpec extends WordSpec  with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks  {

  "Avro4s Encoders" should {
    "encode a DateTime" in forAll (epochLong) { timestamp: Long =>
      case class Foo(s: DateTime)

      val schema = AvroSchema[Foo]
      val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
      val dateTime = DateTime(localDateTime)

      Encoder[Foo].encode(Foo(dateTime), schema) shouldBe ImmutableRecord(schema, Vector(java.lang.Long.valueOf(timestamp)))
    }
  }

}
