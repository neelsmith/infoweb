---
layout: page
title: Ancient Roman tweeting
---

> Abstract: I want to compare the state-sponsored messaging disseminated on coins across five centuries of the Roman Empire to modern tweeting. The fundamental data source is the *Online Coins of the Roman Empire* <http://numismatics.org/ocre/>.


I'm outlining here my preliminary work with data from the *Online Coins of the Roman Empire* and with *Roman Republican Coins Online* in a series of linked blog posts with accompanying Jupyter notebooks.  Links to notebooks are to `mybinder.org`:  they are also available from <https://github.com/neelsmith/nomisma-jupyter>.

Slides from a talk I co-presented with Thomas Martin and Thomas Posillico as part of the January 4, 2020, panel "Numismatics and Ancient History: New Approaches and Directions" at the American Historical Association annual meeting are [available here](aha/).


## Retrieving and constructing OCRE datasets

 - [A library for working with numismatic data](http://neelsmith.info/hc/2019-11-30-reading-nomisma/), with accompanying notebooks for [OCRE](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FReading_ocre_data.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FReading_ocre_data.ipynb) and for [RCCO](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FReading_crro_data.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FReading_crro_data.ipynb)
 - [Verifying the contents of RDF source data for OCRE](http://neelsmith.info/hc/2019-12-09-validating-RDF/)
 - [Verifying the contents of parsed OCRE data](http://neelsmith.info/hc/2019-12-01-validating-ocre/), with [this notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FVerifying_ocre.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FVerifying_ocre.ipynb)
- [Building a citable text corpus](http://neelsmith.info/hc/2019-12-02-building-ocre-text-corpus/), with [this notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FOcre_text_corpus.ipynb%09Reading_ocre_data.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=building%2FOcre_text_corpus.ipynb)
- Adding geography to a dataset

## Analyzing numismatic data in OCRE

- [Visualizing frequencies of property values](http://neelsmith.info/hc/2019-12-02-frequencies-in-ocre/), with [this notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FFrequencies_ocre.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FFrequencies_ocre.ipynb)
- Geographic distributions, with [this notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FGeography_ocre.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FGeography_ocre.ipynb)
- Chronological distributions, with [this notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FChronology_ocre.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FChronology_ocre.ipynb)

## Analyzing OCRE's textual corpus

- Specifying orthography, with [this Jupyter notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FOcre_char_set.ipynb)  [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FOcre_char_set.ipynb)
- Classified tokenization
- Morphologically sensitive searching, with [this Jupyter notebook](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FMorphological_search_ocre.ipynb) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/nomisma-jupyter/master?filepath=ocre%2FMorphological_search_ocre.ipynb)
