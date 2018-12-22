/*
Utility functions to compose web pages with current data from bintray.
*/

import io.github.neelsmith.btreporter._
import java.io.PrintWriter


val citeRepoNames = Vector("xcite", "ohco2", "citeobj", "scm", "dse")
val greekRepoNames =  Vector("greek", "gsphone")
val hmtRepoNames =  Vector("hmt-textmodel")
val utilRepoNames =  Vector("seqcomp")

val btRepo = BintrayRepo("neelsmith", "maven")

def byName(names: Vector[String]): String = {
  val mdTable = btRepo.markdownTable(names)
  mdTable
}

def byDate(names: Vector[String]): String = {
  val mdTable = btRepo.markdownTableByDate(names)
  mdTable
}



// write markdown files with handcrafted titles and intro text.
def writeCiteByName : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Libraries for the CITE Architecture\n---\n\n"
  new PrintWriter("citelibs.md") { write(hdr + byName(citeRepoNames)); close; }
}
def writeCiteByDate : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Libraries for the CITE Architecture\n---\n\nSorted by last modified date.\n\n"
  new PrintWriter("citebydate.md") { write(hdr + byDate(citeRepoNames)); close; }
}

def writeGreekByName : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Libraries for working with ancient Greek language\n---\n\n"
  new PrintWriter("greeklibs.md") { write(hdr + byName(greekRepoNames)); close; }
}
def writeGreekByDate : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Libraries for working with ancient Greek language\n---\n\nSorted by last modified date.\n\n"
  new PrintWriter("greekbydate.md") { write(hdr + byDate(greekRepoNames)); close; }
}

def writeHmtByName : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Homer Multitext\n---\n\n"
  new PrintWriter("hmtlibs.md") { write(hdr + byName(hmtRepoNames)); close; }
}

def writeUtilsByName : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Other\n---\n\n"
  new PrintWriter("utilslibs.md") { write(hdr + byName(utilRepoNames)); close; }
}


def writeAll: Unit = {
  writeCiteByName
  writeCiteByDate
  writeGreekByName
  writeGreekByDate
  writeHmtByName
  writeUtilsByName
}
