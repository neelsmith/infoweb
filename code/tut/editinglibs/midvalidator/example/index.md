---
layout:  page
title: "Example: analyzing a corpus of texts"
---


##  A useful group of libraries

Implementations of the `MidOrthography` trait simplify several tasks that commonly arise in working with a citable corpus of texts.  Import the `cite` and `ohco2` libraries along with the MID validator:


```scala
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
```

## Tokenize a corpus






Acquire a corpus of citable texts.  For this example, we'll use a very short selection from Livy named `corpus` (created behind the scenes of this page) containing six citable nodes. (If you're curious, [here's the corpus](source).)

```scala
scala> corpus.size
res0: Int = 6
```




An `MidOrthography` understands the semantics of a text's orthography so that it can be analyzed in terms of a specified set of token classes.  For this text, we'll import the `Latin23Alphabet` orthography and tokenize our corpus.


```scala
import edu.holycross.shot.latin.Latin23Alphabet
val tokens = Latin23Alphabet.tokenizeCorpus(corpus)
```


## Sumarize a Vector of tokens

The `MidOrthography` object can work with the resulting Vector of tokens.


Get an overview of the distribution of tokens by category:

```scala
scala> MidOrthography.categoryHistogram(tokens)
res1: scala.collection.immutable.ListMap[Option[edu.holycross.shot.mid.validator.MidTokenCategory],Int] = ListMap(Some(LexicalToken) -> 166, Some(PunctuationToken) -> 28, None -> 19)
```


Get a complete concordance of alphabetically sorted tokens mapped to CtsUrns where they occur.  Passages for each token are sorted by document order.

```scala
scala> val concordance = MidOrthography.concordance(tokens)
concordance: scala.collection.immutable.ListMap[String,Vector[edu.holycross.shot.cite.CtsUrn]] = ListMap(, -> Vector(urn:cts:omar:stoa0179.stoa001.omar:1.4.5.0_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.5.4_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.5.7_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.5.17_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.6.1_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.6.4_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.6.8_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.6.13_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.6.17_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.6.20_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.6.32_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.7.13_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.8.3_0, urn:cts:omar:stoa0179.stoa001.omar:1.4.8.7_0, urn:...

scala> // show passages of URNs where "ferunt" appears
     | concordance("ferunt").map(_.passageComponent)
res3: scala.collection.immutable.Vector[String] = Vector(1.4.5.16, 1.4.7.3)
```


We might want to analyze only the tokens of a particular type:

```scala
scala> val lexical = tokens.filter(_.tokenCategory.toString == "Some(LexicalToken)")
lexical: scala.collection.immutable.Vector[edu.holycross.shot.mid.validator.MidToken] = Vector(MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.0,sacerdos,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.2,in,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.3,custodiam,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.4,datur,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.5,pueros,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.6,in,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.7,profluentem,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa001.omar:1.4.4.8,aquam,Some(LexicalToken)), MidToken(urn:cts:omar:stoa0179.stoa0...
```

Lists of unique vocabulary are alphabetically sorted:

```scala
scala> val vocab = MidOrthography.vocabulary(lexical)
vocab: Vector[String] = Vector(Faustulo, Larentiae, Larentiam, Romularem, Ruminalis, Tiberis, ab, ac, ad, adeo, adiri, aetas, amnis, animisque, aqua, aquam, celebrare, circa, corpore, corporibus, crescente, cum, cursum, custodiam, dabat, datos, datum, datur, defuncti, destituisset, dies, eam, educandos, educati, effusus, eo, erant, est, et, ex, exponunt, expositi, fabulae, facere, fama, feras, ferentibus, ferunt, ficus, flexisse, fluitantem, forte, fuisse, geniti, grege, hinc, his, iam, imperio, impetus, in, inde, infantes, infantibus, inter, iocos, ita, itaque, iubet, iusti, lambentem, languida, latrones, lenibus, lingua, locis, locum, lupam, magister, mammas, mergi, miraculo, mitem, mitti, montibus, nec, nomen, non, nunc, onustos, pastores, pastoribusque, pec...
```

Histograms of tokens are sorted by frequency with secondary alpahbetic sort on the token string.

```scala
scala> val tokenHisto = MidOrthography.tokenHistogram(lexical)
tokenHisto: scala.collection.immutable.ListMap[String,Int] = ListMap(in -> 8, ad -> 4, cum -> 3, nec -> 3, pueros -> 3, ac -> 2, aqua -> 2, cursum -> 2, erant -> 2, et -> 2, ferunt -> 2, his -> 2, ita -> 2, lupam -> 2, qui -> 2, sunt -> 2, Faustulo -> 1, Larentiae -> 1, Larentiam -> 1, Romularem -> 1, Ruminalis -> 1, Tiberis -> 1, ab -> 1, adeo -> 1, adiri -> 1, aetas -> 1, amnis -> 1, animisque -> 1, aquam -> 1, celebrare -> 1, circa -> 1, corpore -> 1, corporibus -> 1, crescente -> 1, custodiam -> 1, dabat -> 1, datos -> 1, datum -> 1, datur -> 1, defuncti -> 1, destituisset -> 1, dies -> 1, eam -> 1, educandos -> 1, educati -> 1, effusus -> 1, eo -> 1, est -> 1, ex -> 1, exponunt -> 1, expositi -> 1, fabulae -> 1, facere -> 1, fama -> 1, feras -> 1, ferentib...
```
