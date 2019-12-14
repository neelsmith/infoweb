---
title:  Automatically generated API documentation
layout: post
tags:  [libs]
---

I recently put together a simple shell script that reads a list of github repositories, pulls and builds API documentation for the current version, and then commits the output to a directory that's served by github pages.  Chris Blackwell and I are using this to track our work on code libraries relevant to our development of the CITE architecture.  The shell script is in [this github repository](https://github.com/cite-architecture/cite-api-docs), and the output is served on github pages at <https://cite-architecture.github.io/cite-api-docs/>.

I was inspired to elaborate on that to track some of the libraries I'm using in various other parts of my professional work.  I've grouped the libraries in categories such as "Greek and Latin" and "Editing digital corpora."  The output [here](http://neelsmith.info/code/auto/) is linked from an [introductory page](http://neelsmith.info/code/).

When you've been doing this kind of work as long as I have, it's  easy to forget the current version of a library. It's also easy to overlook how simple it has become to wire together a task like this from infrastructure like git, and ghpages serving markdown with Jekyll.  Ten years ago, I think automatically generating API documentation, tracking it in a version control system, and posting it on a reliable host would have been a major undertaking.  Today, it's a quick shell script that does its work in about 20-25 lines of code.
