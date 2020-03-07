---
layout: page
title: Ancient Geography
---


I've begun working with a TEI-compliant edition of Ptolemy's *Geography*, and code libraries for parsing the XML text into a geographic database of more than 6,000 points. (See [this repository](https://github.com/neelsmith/ptolemy).)  I'll add links here to Jupyter notebooks analyzing that database.



## Greek text

You can read the digital text of the *Geography* in a web browser here.  
Lookup a passage by canonical reference (book.chapter.section):

  <form onSubmit="return ptol();">
  <input id="psg" type="text" maxlength="10" class="box" value="1.1.1" autofocus />
  <input type="submit" class="submit" value="Lookup" />
  </form>
  <script>
  function ptol(){
      var response = document.getElementById('psg').value;
      var newLoc = 'http://neelsmith.info/current-projects/geography/ptolemy-geography/geography-' + response + '/';
      location = newLoc;
      return false;
  }
  </script>
