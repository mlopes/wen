package wen

import java.time.Instant

import cats.implicits._
import com.sksamuel.avro4s.{Encoder, SchemaFor}
import com.sksamuel.avro4s.Encoder.LongEncoder
import org.apache.avro.Schema
import wen.datetime.DateTime
import wen.instances.iso._

package object avro4s {
  implicit object LocalDateTimeSchemaFor extends SchemaFor[DateTime] {
    override def schema: Schema = Schema.create(Schema.Type.LONG)
  }

  implicit val DateTimeEncoder: Encoder[DateTime] = {
    LongEncoder.comap[DateTime]{ x =>
      val dateTimeAsString: String = f"${x.show}.${x.time.millisecond.millisecond.value}%03dZ"
      Instant.parse(dateTimeAsString).toEpochMilli
    }
  }
}