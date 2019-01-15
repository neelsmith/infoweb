---
layout: page
title:  Syllables
---




A `Syllable` has a vector of `Neume`s.  You can create a Vector of `Syllable`s from a `String` using the `Syllabifier` object.  Syllables are separated by white space;  within a single syllable, neumes are separated by hyphens.

Example:  the string `1.2.0.0 1.2.0.0 0.12.0.0-1.3.0.0` represents three syllables (separated by white space).

```scala
val sylls =  Syllabifier("1.2.0.0 1.2.0.0 0.12.0.0-1.3.0.0")
assert(sylls.size == 3)

```

The first syllable has one neume, a punctum.

```scala
assert (sylls(0).size == 1)
assert(sylls(0).neumes(0).neumeType.get == Punctum)
```

The second syllable is identical,  but the third syllable has two neumes (separated by a hyphen  in the intial string).


```scala
assert (sylls(2).size == 2)
```
