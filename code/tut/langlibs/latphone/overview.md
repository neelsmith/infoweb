---
layout: page
title: "Overview"
---

## Orthography of Latin

At the core of the library is an extendable `LatinAlphabet` trait.  The `LatinString` class associates a string of characters with an implementation of this trait, and all processing of Latin strings takes account of the orthographic system used to compose the string.

In all alphabets, white space characters separate blocks of one or more tokens, but are not otherwise significant.  Implementations of a `LatinAlphabet` must explicitly enumerate all allowed non-white-space characters, and classify them in various ways (alphabetic characters versus punctuation, for example, but alphabetic characters are also identified as vowels, consonants or semivowels).

## Numeric characters

A single set of numeric characters is defined for all implementations of `LatinAlphabet`:  it is a subset of the Unicode codepoints defined for Roman numerals in the "Number forms" section of Unicode, and is [summarized in this table](../numerics).

The syntax of numeric strings can be validated, and integer values computed for arbitrary numeric strings.

## Tokenization

When a string of characters in a particular alphabet occurs in a node of citable text, it can be parsed into a sequence of citable `LatinToken`s. Each token is classified into a particular `LatinLexicalCategory` based on its composition of alphabetic, numeric or punctuation characters.


## Syllabification

Given this definition of a `LatinAlphabet`, lexical strings in a specified alphabet can be automatically broken into a sequence of syllables.
