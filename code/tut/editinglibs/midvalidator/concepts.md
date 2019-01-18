---
layout:  page
title: "Background: key concepts"
---

The `midvalidator` relies on the CITE Architecture's model of documented scholarly editions.

The library introduces two key concepts related to the contents of these editions:  the *reader* and the *orthography*.


## `MidMarkupReader`

Classes implementing the `MidMarkupReader` are able to convert multivalent documents into one or more individual editions.

Example:  objects of the `MidProseABReader` class can read TEI XML cited at two levels organized with TEI `div` and `ab` elements.  It is created with an `MidEditionType`.



```scala
scala> import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.validator._

scala> val reader = MidProseABReader(MidDiplomaticEdition)
reader: edu.holycross.shot.mid.validator.MidProseABReader = MidProseABReader(MidDiplomaticEdition)
```



`MidMarkupReader`s are required to identify all the types of editions they recognize.  For the version of `MidProseABReader`, that is only the `MidDiplomaticEdition` type.


```scala
scala> reader.editionTypes
res0: Vector[edu.holycross.shot.mid.validator.MidEditionType] = Vector(MidDiplomaticEdition)
```

The reader can create a new edition from a citable node:

```scala
scala> import edu.holycross.shot.cite._
import edu.holycross.shot.cite._

scala> val xml = "<div n=\"1\"><ab n=\"1\">Text 1<del>.1</del><add>.2</add> version</ab></div>"
xml: String = <div n="1"><ab n="1">Text 1<del>.1</del><add>.2</add> version</ab></div>

scala> val urn =CtsUrn("urn:cts:mid:unittests.1.xml:1.1")
urn: edu.holycross.shot.cite.CtsUrn = urn:cts:mid:unittests.1.xml:1.1

scala> reader.editedNode(xml, urn)
res1: String = "urn:cts:mid:unittests.1.xml_dipl:1.1#Text 1 .1 version "
```

The `MidProseABReader` throws an exception when it encounters unrecognized markup.

```scala
scala> val badXml = "<div n=\"1\"><ab n=\"1\"><watermark>Agamemnon</watermark></ab></div>"
badXml: String = <div n="1"><ab n="1"><watermark>Agamemnon</watermark></ab></div>

scala> val urn2 =CtsUrn("urn:cts:mid:unittests.2.xml:1.1")
urn2: edu.holycross.shot.cite.CtsUrn = urn:cts:mid:unittests.2.xml:1.1

scala> try {
     |   reader.editedNode(badXml, urn2)
     | } catch {
     |   case e: Exception => println("Failed to create diplomatic edition: " + e.getMessage)
     | }
Failed to create diplomatic edition: Unrecognized XML element: watermark
res2: Any = ()
```

## `MidOrthography`

An `MidOrthography` defines not only what code points are permissible in edition, but also how they are composed to create tokens of particular types.  As the `MidMarkupReader` is required to identify what kinds of editions it can create, the `MidOrthography` is required to identify what kinds of tokens it can recognize.

Like the `MidMarkupReader`, the `MidOrthography` is defined as a Scala trait that can be implemented in other libraries.

The `latphone` library's `Latin23Alphabet` is an example.  It recognizes five types of tokens.

```scala
scala> import edu.holycross.shot.latin._
import edu.holycross.shot.latin._

scala> Latin23Alphabet.tokenCategories
res3: Vector[edu.holycross.shot.mid.validator.MidTokenCategory] = Vector(PraenomenToken, PunctuationToken, LexicalToken, NumericToken, InvalidToken)
```


It implements the required function determining if a code point is allowed or not.

```

val a = 'a'.toInt
val alpha =  'α'.toInt

Latin23Alphabet.validCP(a)
Latin23Alphabet.validCP(alpha)

```

It also implements the required function tokenizing a `CitableNode` into a Vector of tokens.

```
import edu.holycross.shot.ohco2._

val n = CitableNode(CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:2.8.4"), "creatus Sp. Lucretius consul,")

val tokens = Latin23Alphabet.tokenizeNode(n)
println(tokens.mkString("\n"))
```
