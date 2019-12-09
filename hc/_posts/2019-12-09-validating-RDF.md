---
title:  Verifying the RDF source data for OCRE
layout: post
tags:  [coins]
---

[An earlier post](http://neelsmith.info/hc/2019-12-01-validating-ocre/) showed how to get a quick overview of data values in an `Ocre`-class object created by parsing RDF data downloaded from nomisma.org.  Before we import the RDF data, however, we should do a similar survey of exactly what the RDF contains. (Yes, I should more logically have posted these notes first.)

While the contents available through OCRE's interactive app appear to be generally stable, the downloadable RDF files can differ significantly in size.  Compare the number of bytes (from `ls -l`) of two downloads, one from October, 2018, and the second from Nov. 28, 2019.

    113553331 Oct 18  2018 ocre-2018-10.rdf
    151588482 Nov 29 08:53 ocre-2019-11-28.rdf

The files do not include any versioning information, so I rely on the download date to identify a specific version of the OCRE data.

Parsing this much XML requires more memory than the 2 Mb maximum available on `mybinder.org`, so instead of including a Jupyter notebook with this post, I'm embedding Scala code here, copying and pasting from a running Scala REPL session.

Using version 2.1.0 or higher, we first import the `nomisma` library.  We'll download a file mirrored from nomisma.org on Nov. 28, 2019, when the nomisma.org website noted that the file included records for `50645` issues.

We'll parse it into a structure that mirrors the organization of the RDF.  Basic data about each issue are in a Vector named `issues`.

```scala
import edu.holycross.shot.nomisma._

val url = "http://shot.holycross.edu/nomisma/ocre-2019-11-28.rdf"
val rdf = NomismaRdfCollectionSource.fromUrl(url)
println("Number of issues: " + rdf.issues.size)
```

    Number of issues: 51267

What's going on?  Why are there more issues than nomisma.org lists?  Are there duplicate records?


```scala
val extraRecords = rdf.issues.size - rdf.issues.distinct.size

println("Number of distinct issues: " + rdf.issues.distinct.size)
println("Number of duplicated records: " + extraRecords)
```

    Number of distinct issues: 50645
    Number of duplicated records: 622

So we have exactly the number of distinct records that nomisma.org showed, but 622 extraneous records. How are they distributed?  622 copies of one issue, two copies each of 622 issues, or something else?

We can use Scala's `groupBy` function to cluster issues with a common value, keyed to that value, then keep only clusters of issues appearing more than once.  That will tell us how many distinct RIC issues have duplicate records.

```scala
val duplicatedGroups = rdf.issues.groupBy( issue => issue).filter(_._2.size > 1)
println("Issues with more than one record:  " + duplicatedGroups.size)
```

    Issues with more than one record:  622

So there are 622 issues each with 1 duplicate record.  Let's see what their RIC IDs look like:

```scala
val duplicateIds = duplicatedGroups.keySet.map(_.id).toVector.mkString("\n")
```    

    duplicateIds: String =
    1_2.ner.447
    1_2.ner.59
    1_2.ner.328
    1_2.ner.299
    1_2.ner.475
    1_2.ner.505
    1_2.ner.162
    1_2.ner.407
    1_2.ner.489
    ...

It quickly becomes clear that somehow all records of Nero RIC 1.2 appear twice!


## Deduplicating records

How should we handle duplicate records?  The first version of the `nomisma` library simply rejected all records with non-unique IDs.  This is not unreasonable, but after verifying that the extra entries in OCRE are simply duplicates, and not cases where a single identifier has been misapplied to two distinct records, we can adopt a different approach. Beginning with version `2.1.0`, the `nomisma` library accepts all *distinct* records from RDF source.  Scala's `distinct` function eliminates duplicate elements only if all member values of a structure are equal. We can use `distinct` and be sure we aren't rejecting two different records that happen to have overlapping ID values dues to a data entry error.


## Checking ID values

At this point, we have identified 50645 unique (distinct) records in nomisma.org's RDF, as the nomisma.org website suggests we should find.  Before we try to parse the unique records, however, we should check that they have valid identifiers.  A basic check would be to ensure that unique identifiers are not empty:

```scala
println("Unique, non-empty identifiers: " + root.issues.map(_.id).filter(_.nonEmpty).size)

```

    Unique, non-empty identifiers: 50644

Apparently, the 50645 unique records include one with an empty ID value. A little poking around the RDF source uncovers a record wedged in between the records for `http://numismatics.org/ocre/id/ric.10.theo_ii_e.201` and  `http://numismatics.org/ocre/id/ric.10.arc_e.159`:


```
<nmo:TypeSeriesItem xmlns:un="http://www.owl-ontologies.com/Ontology1181490123.owl#"
                        xmlns:crm="http://www.cidoc-crm.org/cidoc-crm/"
                        xmlns:numishare="https://github.com/ewg118/numishare"
                        rdf:about="http://numismatics.org/ocre/id/">
        <rdf:type rdf:resource="http://www.w3.org/2004/02/skos/core#Concept"/>
        <skos:prefLabel xml:lang="en">RIC   </skos:prefLabel>
        <skos:definition xml:lang="en">RIC   </skos:definition>
        <dcterms:source rdf:resource="http://nomisma.org/id/ric"/>
        <nmo:hasManufacture rdf:resource="http://nomisma.org/id/struck"/>
        <void:inDataset rdf:resource="http://numismatics.org/ocre/"/>
</nmo:TypeSeriesItem>
```


## Summary

The RDF download from November, 2019, included 51267 records.  50645 of them were unique records.  50644 of them were unique, non-empty records.  The delimited text file at <https://raw.githubusercontent.com/neelsmith/nomisma/master/cex/ocre-cite-ids.cex> is a delimited text representation of those 50644 records, with IDs remapped as [documented previously at the end of this post](http://neelsmith.info/hc/2019-12-02-building-ocre-text-corpus/).

Working with downloaded versions of OCRE's RDF allows us to identify a snapshot of the data at a specific moment, and apply convenient tools to assess its structure and consistency.
