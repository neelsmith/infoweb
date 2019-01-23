---
layout:  page
title: "Background: key concepts"
---

The `midvalidator` relies on the CITE Architecture's model of documented scholarly editions.

The library introduces two key concepts related to the contents of these editions:  the *reader* and the *orthography*.


## `MidMarkupReader`

Classes implementing the `MidMarkupReader` trait are able to convert multivalent documents into one or more individual editions.

*Example*:  objects of the `MidProseABReader` class can read TEI XML with two levels of citation organized with TEI `div` and `ab` elements.  You create an `MidProseABReader` with a parameter identifying the type of edition you want to work with (an instance of a `MidEditionType`)


```scala
scala> import edu.holycross.shot.mid.validator._
import edu.holycross.shot.mid.validator._

scala> val reader = MidProseABReader(MidDiplomaticEdition)
reader: edu.holycross.shot.mid.validator.MidProseABReader = MidProseABReader(MidDiplomaticEdition)
```



`MidMarkupReader`s are required to identify *all* the types of editions they recognize.  For the version of `MidProseABReader` used in this tutorial, that is only the `MidDiplomaticEdition` type.


```scala
scala> reader.recognizedTypes
res0: Vector[edu.holycross.shot.mid.validator.MidEditionType] = Vector(MidDiplomaticEdition)
```

The reader can create a new edition of a citable node as a string in CEX format.

```scala
scala> import edu.holycross.shot.cite._
import edu.holycross.shot.cite._

scala> val xml = "<div n=\"1\"><ab n=\"1\">Text 1<del>.1</del><add>.2</add> version</ab></div>"
xml: String = <div n="1"><ab n="1">Text 1<del>.1</del><add>.2</add> version</ab></div>

scala> val urn =CtsUrn("urn:cts:mid:unittests.1.xml:1.1")
urn: edu.holycross.shot.cite.CtsUrn = urn:cts:mid:unittests.1.xml:1.1

scala> reader.editedNodeCex(urn, xml)
res1: String = "urn:cts:mid:unittests.1.xml_dipl:1.1#Text 1 .1 version "
```

The `MidProseABReader` throws an exception when it encounters unrecognized markup.

```scala
scala> val badXml = "<div n=\"1\"><ab n=\"1\"><watermark>Agamemnon</watermark></ab></div>"
badXml: String = <div n="1"><ab n="1"><watermark>Agamemnon</watermark></ab></div>

scala> val urn2 =CtsUrn("urn:cts:mid:unittests.2.xml:1.1")
urn2: edu.holycross.shot.cite.CtsUrn = urn:cts:mid:unittests.2.xml:1.1

scala> try {
     |   reader.editedNodeCex(urn2, badXml)
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

```scala
scala> val a = 'a'.toInt
a: Int = 97

scala> val alpha =  'α'.toInt
alpha: Int = 945

scala> Latin23Alphabet.validCP(a)
res4: Boolean = true

scala> Latin23Alphabet.validCP(alpha)
res5: Boolean = false
```

It also implements the required function tokenizing a `CitableNode` into a Vector of tokens.

```scala
scala> import edu.holycross.shot.ohco2._
import edu.holycross.shot.ohco2._

scala> val n = CitableNode(CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:2.8.4"), "creatus Sp. Lucretius consul,")
n: edu.holycross.shot.ohco2.CitableNode = CitableNode(urn:cts:omar:stoa0179.stoa001.omar:2.8.4,creatus Sp. Lucretius consul,)

scala> val tokens = Latin23Alphabet.tokenizeNode(n)
tokens: Vector[edu.holycross.shot.mid.validator.MidToken] = Vector(MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.0,creatus,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.1,Sp.,Some(PraenomenToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.2,Lucretius,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.3,consul,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.3_0,,,Some(PunctuationToken)))

scala> println(tokens.mkString("\n"))
MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.0,creatus,Some(LexicalToken))
MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.1,Sp.,Some(PraenomenToken))
MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.2,Lucretius,Some(LexicalToken))
MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.3,consul,Some(LexicalToken))
MidToken(urn:cts:omar:stoa0179.stoa001.omar:2.8.4.3_0,,,Some(PunctuationToken))
```
