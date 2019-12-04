---
title:  Building a citable text corpus of OCRE coin legends
layout: post
tags:  [coins]
---

[![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FOcre_text_corpus.ipynb)

## TL;DR

You can create a citable text corpus with the `Ocre` class' `corpus` function.  See examples in [a Jupyter notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FOcre_text_corpus.ipynb).


## OCRE legends as a text corpus

One compelling reason to work with nomisma.org's data outside of the webapp's Linked Open Data framework is to apply libraries for analyzing text corpora to OCRE's coin legends.  In subsequent blog posts, I will use tested Scala libraries first to validate that the legends comply with a defined corpus-specific orthographic system, and then to produce classified tokenizations of the texts. These analyses in turn serve as input to a corpus-specific morphological parsing system.

But before we analyze the contents of coin legends, we need to organize them in a citable corpus associating each legend with a CTS URN.  (The CTS URN is a proposed IETF standard currently in expert review by the IETF's URN group.)

Constructing a text corpus is a single line with the `nomisma` library, once we bring in the `cite` and `ohco2` libraries from the CITE architecture.

```scala
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

val corpus: Corpus = ocre.corpus
```
Under the hood, the `OcreIssue` class has a `textNodes` function that creates 0-2 citable text nodes. (There wil be two text nodes if the issue has both an obverse and reverse legend.)

```scala
val issueId = "3.com.43"
val randomIssue = ocre.issue(issueId).get

println("In issue " + issueId + ", made " + randomIssue.textNodes.size + " text nodes")

for (n <- randomIssue.textNodes) {
    println("\nReference: " + n.urn)
    println("Text content: " + n.text)
}
```

    In issue 3.com.43, made 2 text nodes

    Reference: urn:cts:hcnum:issues.ric.raw:3.com.43.obv
    Text content: M COMMODVS ANTONINVS AVG

    Reference: urn:cts:hcnum:issues.ric.raw:3.com.43.rev
    Text content: TR P VII IMP V COS III P P

Let's parse the components of the identifying URNs.

They belong to the CTS namespace `hcnum`, and a text group `issues`.  

Within that group, their document identifier is `ric`, and the specific version identifier is `raw`. When we process the corpus (e.g., to generate a fully expanded version of abbreviated terms), we will use a different version identifier, but the rest of the URN will remain the same.

The passage component is directly adapted from the nomisma.org identifier:  `3.com.43` identifies RIC volume 3, Commodus, issue 43.  The final piece of the passage component distinguishes obverse text from reverse text.

As in any CTS environment, we can then select texts identified at any level of the passage and work hierarchies.


```scala
val commodus43 = corpus.nodes.filter(_.urn <=  CtsUrn("urn:cts:hcnum:issues.ric.raw:3.com.43"))
println("**OBV** " + commodus43.map(_.text).mkString(" **REV** "))
```

    **OBV** M COMMODVS ANTONINVS AVG **REV** TR P VII IMP V COS III P P

```scala
val allCommodus = corpus.nodes.filter(_.urn <= CtsUrn("urn:cts:hcnum:issues.ric.raw:3.com"))
println("All legends in coins of Commodus: " + allCommodus.size)
```   

    All legends in coins of Commodus: 1890


```scala
val allRIC3 = corpus.nodes.filter(_.urn <= CtsUrn("urn:cts:hcnum:issues.ric.raw:3"))
println("All legends in RIC 3: " + allRIC3.size)
```
    All legends in RIC 3: 10250


## Immutable identifiers

The [Jupyter notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FOcre_text_corpus.ipynb) accompanying this post shows *how* the `nomisma` library works with a citable corpus, but I want to comment briefly on the choices underlying that implementation.

Identifiers have no meaning if they can be changed.  They can be deprecated and mapped to other identifiers, but a reference that can be changed is lost.

Nomisma.org, like other Linked Open Data projects, uses URLs to identify content.  The texts in our corpus use CTS URNs.  We must be able to associate every URN in our text corpus with the nomisma.org URL for the corresponding coin.  URLs and URNs have different restrictions on character usage, however. The hyphen `-`, for example, is a reserved character in CTS URNs used to join two endpoints of a range of text, but nomisma.org URLs frequently use hyphens in their identifiers.  In generating corresponding URNs, hyphens are replaced with underscores `_`.  Nomisma.org URLs *also* include underscores in their identifiers, however, so to support 100% conversion in both directions (URN<->URL) I have maintained a simple text file in the `nomisma` library's github repository <https://raw.githubusercontent.com/neelsmith/nomisma/master/cex/ocre-url-cts-map.tsv> pairing each original nomisma.org URL for a coin with  the CTS URN for the coin's texts.

Systematically testing all of these relations has advantages. For example, the identifying component `9.alex.21B1.` of a nomisma.org URL fails to parse as an identifying component in a CTS URN since the trailing period is illegal in CTS URNs.  In tracking down why one identifier out of more than 50,000 failed in this way, it became immediately apparent that it was a typo for `9.alex.21B.1`.

A README file in the git repository's cex directory summarizes all the differences between the final identifying components of nomisma.org URLs and the URNs used in the `nomisma` library that are fully cataloged in <https://raw.githubusercontent.com/neelsmith/nomisma/master/cex/ocre-url-cts-map.tsv>.
