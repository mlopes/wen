[![Build Status](https://travis-ci.org/mlopes/wen.svg?branch=master)](https://travis-ci.org/mlopes/wen) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# wen

> 'Wen considered the nature of time and understood that the universe is, instant by instant, recreated anew. Therefore, he understood, there is in truth no past, only a memory of the past. Blink your eyes, and the world you see next did not exist when you closed them. Therefore, he said, the only appropriate state of the mind is surprise. The only appropriate state of the heart is joy. The sky you see now, you have never seen before. The perfect moment is now. Be glad of it.'
>
> ― Terry Pratchett, Thief of Time

Date and time types and instances

## Status

Wen is in early development. There is no release yet.

## What is Wen?

Wen provides types to safely represent months, weekdays, years, epoch, hours, minutes,
seconds and milliseconds, as well as instances of the `Order`, `Eq` and `Show` cats type classes.

Should cats ever support the `Enum` and `Bounded` type classes, and we should implement those as
well, for the relevant types.

### What Wen isn't?

Wen does not aim for date/time related functionality like the one provided by the
`java.time`. Its purpose is to provide stateless representations for date/time data types.
