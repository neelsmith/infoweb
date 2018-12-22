/*
Utility functions to compose web pages with current data from bintray.
*/

import io.github.neelsmith.btreporter._
import java.io.PrintWriter


val citeRepoNames = Vector("xcite", "ohco2", "citeobj", "scm", "dse")


val btRepo = BintrayRepo("neelsmith", "maven")



def citeByName : String = {
  val mdTable = btRepo.markdownTable(citeRepoNames)
  mdTable
}

def citeByDate: String = {
  val mdTable = btRepo.markdownTableByDate(citeRepoNames)
  mdTable
}

def writeCiteByName : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Libraries for the CITE Architecture\n---\n\n"
  new PrintWriter("citelibs.md") { write(hdr + citeByName); close; }
}

def writeCiteByDate : Unit = {
  val hdr = "---\nlayout: page\ntitle:  Libraries for the CITE Architecture\n---\n\nSorted by last modified date.\n\n"
  new PrintWriter("citebydate.md") { write(hdr + citeByDate); close; }
}
