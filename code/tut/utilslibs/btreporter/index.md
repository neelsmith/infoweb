# Scripting with `btreporter`


You need these imports and implicits:

```scala
import net.liftweb.json._
import io.github.neelsmith.btreporter._
import scala.io.Source

implicit val formats = DefaultFormats
```

Instantiate a repository as a `BintrayRepo` object:

```scala
val owner = "neelsmith"
val repo = "maven"
val btRepo = BintrayRepo(owner, repo)
```


Get a bunch of package objects (`BintrayPackage`s) back for a vector of requested package names:


```scala
val pkgNames = Vector("xcite", "citeobj", "ohco2", "greek")
val pkgs =  btRepo.bintrayPackages(pkgNames).flatten
```

Sort your packages alphabetically by name and get a markdown table for the list:

```scala
scala> val rows = pkgs.sortWith(_.name < _.name).map(_.markdownRow)
rows: scala.collection.immutable.Vector[String] = Vector(| `citeobj` | **7.2.0** | January 10, 2019 | A ccross-platform library for working with collections of citable objects. | [https://github.com/cite-architecture/citeobj](https://github.com/cite-architecture/citeobj) | [ ![Download](https://api.bintray.com/packages/neelsmith/maven/citeobj/images/download.svg) ](https://bintray.com/neelsmith/maven/citeobj/_latestVersion) |, | `greek` | **1.4.0** | March 29, 2018 | A cross-platform library for working with strings representing ancient Greek in a variety of alphabetic systems. | [https://github.com/neelsmith/greek](https://github.com/neelsmith/greek) | [ ![Download](https://api.bintray.com/packages/neelsmith/maven/greek/images/download.svg) ](https://bintray.c...

scala> btRepo.markdownTableHeader + rows.mkString("\n")
res0: String =
| Package	| Current version |	Published	|  Summary |	Github repository |	Binary download |
|:---------|:---------|:---------|:---------|:---------|:---------|
| `citeobj` | **7.2.0** | January 10, 2019 | A ccross-platform library for working with collections of citable objects. | [https://github.com/cite-architecture/citeobj](https://github.com/cite-architecture/citeobj) | [ ![Download](https://api.bintray.com/packages/neelsmith/maven/citeobj/images/download.svg) ](https://bintray.com/neelsmith/maven/citeobj/_latestVersion) |
| `greek` | **1.4.0** | March 29, 2018 | A cross-platform library for working with strings representing ancient Greek in a variety of alphabetic systems. | [https://github.com/neelsmith/greek](https://github.com/neelsmith/gr...
```

To sort by last update, you can compare packages' `updateDT` value.  Since `updateDT` is a `joda-time` date, one easy way to do this is just a numeric comparison on their milliseconds value:

```scala
scala> val lastUpdated = pkgs.sortWith(_.updateDT.getMillis < _.updateDT.getMillis).map(_.markdownRow)
lastUpdated: scala.collection.immutable.Vector[String] = Vector(| `greek` | **1.4.0** | March 29, 2018 | A cross-platform library for working with strings representing ancient Greek in a variety of alphabetic systems. | [https://github.com/neelsmith/greek](https://github.com/neelsmith/greek) | [ ![Download](https://api.bintray.com/packages/neelsmith/maven/greek/images/download.svg) ](https://bintray.com/neelsmith/maven/greek/_latestVersion) |, | `xcite` | **3.7.0** | January 10, 2019 | A cross-platform library for semantic manipulation of scholarly references expressed in URN notation. | [https://github.com/cite-architecture/xcite.git](https://github.com/cite-architecture/xcite.git) | [ ![Download](https://api.bintray.com/packages/neelsmith/maven/xcite/images/d...

scala> btRepo.markdownTableHeader + rows.mkString("\n")
res1: String =
| Package	| Current version |	Published	|  Summary |	Github repository |	Binary download |
|:---------|:---------|:---------|:---------|:---------|:---------|
| `citeobj` | **7.2.0** | January 10, 2019 | A ccross-platform library for working with collections of citable objects. | [https://github.com/cite-architecture/citeobj](https://github.com/cite-architecture/citeobj) | [ ![Download](https://api.bintray.com/packages/neelsmith/maven/citeobj/images/download.svg) ](https://bintray.com/neelsmith/maven/citeobj/_latestVersion) |
| `greek` | **1.4.0** | March 29, 2018 | A cross-platform library for working with strings representing ancient Greek in a variety of alphabetic systems. | [https://github.com/neelsmith/greek](https://github.com/neelsmith/gr...
```
