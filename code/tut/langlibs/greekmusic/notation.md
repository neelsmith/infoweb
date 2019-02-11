---
layout: page
title: The notation scheme
---


Outline of the notation devised by Zachary Sowerby for recording ancient Greek musical notation:


- The notation is plain text, with white space delimiting syllables containing one or more notes.
- Multiple notes within a syllable are separated by a tilde `~`
- Notes belong to a class that is indicated by the first four characters of the note identifier:
    - `gvn:`  Greek vocalic notation
    - `gin:`  Greek instrumental notation
    - `gcn:`  Greek common notation (the leimma)
- Following the four-character class, notes are identified by five dot-separated integers:
    1.  pitch (1-24)
    2.  accidental (1-3 to indicate first to third sharp)
    3.  rhythmic value (1-5 to indicate multiple of a metrically "short" syllable)
    4.  arsis (1 if a stigme is present, 0 if a stigme is not present)
    5.  hyphen (1 for presence of hyphen, 0 for absence of hyphen)
