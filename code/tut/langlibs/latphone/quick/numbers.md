---
title: Numeric tokens
layout: page
---


The `LatinNumerics` object can validate the syntax of a Latin numeric string.

```scala
import edu.holycross.shot.latin._

assert(LatinNumerics.valid("ⅩⅣ"))

assert(LatinNumerics.valid("ⅣⅩ") == false)
```

It can also convert valid values to integers.


```scala
assert (LatinNumerics.numericToInt("ⅩⅣ") ==  14)
```

Note that the numeric characters are drawn from the Unicode code points [summarized here](https://neelsmith.github.io/latphone/numerics/).  If you try to use non-numeric characters, it is an exception.

```scala
scala> // True numeric characters:
     | assert(LatinNumerics.valid("ⅩⅣ"))

scala> // Similar looking glyphs for alphabetic characters:
     | try {
     |   LatinNumerics.valid("XIV")
     | } catch {
     |   case t: Throwable => t
     | }
res6: Any = java.lang.Exception: LatinNumerics: X is not a valid numeric character.
```
