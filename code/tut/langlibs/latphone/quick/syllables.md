---
title: Syllabifying words
layout: page
---



Create a `LatinString` by associating a string value with a specific alphabet class.

Example:  the `Latin23Alphabet` alphabet uses 23 alphabetic characters (no "j" or "v").

```scala
import edu.holycross.shot.latin._
val latString = LatinString("conuocare", Latin23Alphabet)
assert(latString.s == "conuocare")
```


Syllabifying a `LatinString` creates a Vector of string values.

```scala
val syllables = latString.syllabify
assert (syllables.size == 4)

```

It's fun to use scala's handy collection methods with Vectors of strings.

```
val syllabicString = syllables.mkString("-")
assert(syllabicString == "con-uo-ca-re")
```
