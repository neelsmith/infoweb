---
title:  A library for working with nomisma.org data
layout: post
tags:  [coins]
---

  > *Nomisma.org is a collaborative project to provide stable digital representations of numismatic concepts according to the principles of [Linked Open Data](https://www.w3.org/DesignIssues/LinkedData.html).* (from  <http://nomisma.org>)

Nomisma.org's collaboration has led to a growing series of LOD applications on the web, but what may be the project's most valuable contribution has sometimes been overlooked: from <http://nomisma.org/datasets>, you can download openly licensed datasets for all nomisma.org projects.  The collaborators have had to establish standard representations of numismatic data for their applications to interact, so the datasets use predictable identifiers in predictable data structures.  This opens the door to scholars interested in using the data in other ways.


## Loading data with the `nomisma` library


The [`nomisma` library](https://github.com/neelsmith/nomisma) is a cross-platform library for working with this freely available numismatic data.  It defines classes that can read the RDF data from nomisma.org, and build objects modeling numismatic concepts like issues, mints, and hoards.  Written in Scala, it is compiled to both JVM and javascript environments; binaries are available from JCenter.  (See the [github repository](https://github.com/neelsmith/nomisma) for details.)

With this week's release of the 1.x series, the library can read and model data from the *Online Coins of the Roman Empire* project (OCRE, <http://numismatics.org/ocre/>).

Here's an illustration testing the library on a small sample of four issues in nomisma.org's RDF format:

```scala
import edu.holycross.shot.nomisma._
val sampleRdf = "https://raw.githubusercontent.com/neelsmith/nomisma/master/jvm/src/test/resources/ocre_sample.rdf"
val sampleOcre = OcreSource.fromRdfUrl(sampleRdf)

// Check results:
val expectedSize = 4
require(sampleOcre.size == expectedSize)
```
Loading and parsing  OCRE's RDF is expensive (a download of the RDF data checks in at more than 150 megabytes), but not something we need to do regularly:  the OCRE dataset seems to be fairly stable.  The `Ocre` class can generate a simple delimited text summary of the data for each issue:

```scala
val delimitedText: String = sampleOcre.cex
```

Applied to the 150 megabyte source from nomisma.org, the resulting string is a little over 19 megabytes.  It represents each issue in 15 columns delimited by the pound sign `#`, and includes a header row.

```scala
val rows = delimitedText.split("\n")

val expectedHeader = "ID#Label#Denomination#Metal#Authority#Mint#Region#ObvType#ObvLegend#ObvPortraitId#RevType#RevLegend#RevPortraitId#StartDate#EndDate"
require(rows(0) == expectedHeader)

val columnLabels = rows(0).split("#")
val expectedColumns = 15
require (columnLabels.size == expectedColumns)

require(rows.tail.size == expectedSize)
```

The `nomisma` github repository includes this delimited text representation of the full OCRE dataset in the `cex` directory.  (The README includes information on the download date of the RDF the current cex file was built from.)  Loading and parsing the CEX representation is a single function and tolerably fast over a broadband internet connection:

```scala
val ocreCex = "https://raw.githubusercontent.com/neelsmith/nomisma/master/cex/ocre-cite-ids.cex"
val ocre = OcreSource.fromUrl(ocreCex)

// check results:
require(ocre.size > 50000)    
```
Of course you could download the CEX file and quickly build an `Ocre` object from that, with the parallel `OcreSource.fromFile(FILENAME)` function.

## Using the `Ocre` object

Following blog posts will illustrate how to use the `Ocre` object's data in ways that complement the valuable existing functions in the [OCRE web app](http://numismatics.org/ocre/).

## Jupyter notebooks

The `jupyter` directory of the github repository includes Jupyter notebooks illustrating how to use the `nomisma` library.  You can open them in a notebook server locally running, or on `mybinder.org` [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma/master)




 .
