---
layout: page
title:  Neumes
---




The basic object in the library is a `Neume`.  You can create it from a `String` in virgapes notation, and then work with the neume's components.

Example:  the string `1.2.0.0` represents a one-syllable neume with ID 2, no episema (ie, episema on syllable 0), and no liquescence (ie, liquescenceon syllable 0).


```scala
scala> import edu.holycross.shot.virgapes._
import edu.holycross.shot.virgapes._

scala> val neume = Neume("1.2.0.0")
neume: edu.holycross.shot.virgapes.Neume = 1.2.0.0

scala> assert(neume.name == "punctum")

scala> assert(neume.pitches == 1)

scala> assert(neume.episema == 0)

scala> assert(neume.liquescence == 0)
```

You can also retrieve a `NeumeType`  option for a `Neume`.

```scala
scala> val nType = neume.neumeType
nType: Option[edu.holycross.shot.virgapes.NeumeType] = Some(Punctum)

scala> assert(nType.get == Punctum)
```

See the [API docs](http://neelsmith.info/code/api-docs/langlibs/virgapes/edu/holycross/shot/virgapes/) for  more information.
