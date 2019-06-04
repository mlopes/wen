package wen

import java.time.Instant

import cats.implicits._
import com.sksamuel.avro4s.{Encoder, SchemaFor}
import com.sksamuel.avro4s.Encoder._
import org.apache.avro.Schema
import wen.datetime._
import wen.instances.iso._
import wen.types._

package object avro4s {
  implicit object DateTimeSchemaFor extends SchemaFor[DateTime] {
    override def schema: Schema = Schema.create(Schema.Type.LONG)
  }

  implicit val DateTimeEncoder: Encoder[DateTime] = {
    LongEncoder.comap[DateTime] { x =>
      val dateTimeAsString: String = f"${x.show}.${x.time.millisecond.millisecond.value}%03dZ"
      Instant.parse(dateTimeAsString).toEpochMilli
    }
  }

  implicit object TimeSchemaFor extends SchemaFor[Time] {
    override def schema: Schema = Schema.create(Schema.Type.INT)
  }

  implicit val TimeEncoder: Encoder[Time] = {
    IntEncoder.comap[Time] { x =>
      x match {
        case Time(Hour(h), Minute(m), Second(s), Millisecond(ms)) =>
          (h.value * 60 * 60 * 1000) +
          (m.value * 60 * 1000) +
          (s.value * 1000) +
          ms.value
      }
    }
  }
}