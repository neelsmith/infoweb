---
layout:  page
title: "Text source for example"
---


CEX source and scala code to create the corpus used in this example:


```scala
import edu.holycross.shot.ohco2._
val cex = """urn:cts:omar:stoa0179.stoa001.omar:1.4.4#sacerdos vincta in custodiam datur; pueros in profluentem aquam mitti iubet. forte quadam divinitus super ripas Tiberis effusus lenibus stagnis nec adiri usquam ad iusti cursum poterat amnis et posse quamvis languida mergi aqua infantes spem ferentibus dabat.
urn:cts:omar:stoa0179.stoa001.omar:1.4.5#ita, velut defuncti regis imperio, in proxima eluvie, ubi nunc ficus Ruminalis est — Romularem vocatam ferunt —, pueros exponunt. vastae tum in his locis solitudines erant.
urn:cts:omar:stoa0179.stoa001.omar:1.4.6#tenet fama, cum fluitantem alveum, quo expositi erant pueri, tenuis in sicco aqua destituisset, lupam sitientem ex montibus, qui circa sunt, ad puerilem vagitum cursum flexisse; eam summissas infantibus adeo mitem praebuisse mammas, ut lingua lambentem pueros magister regii pecoris invenerit—
urn:cts:omar:stoa0179.stoa001.omar:1.4.7#Faustulo fuisse nomen ferunt —; ab eo ad stabula Larentiae uxori educandos datos. sunt, qui Larentiam vulgato corpore lupam inter pastores vocatam putent; inde locum fabulae ac miraculo datum.
urn:cts:omar:stoa0179.stoa001.omar:1.4.8#ita geniti itaque educati, cum primum adolevit aetas, nec in stabulis nec ad pecora segnes, venando peragrare saltus.
urn:cts:omar:stoa0179.stoa001.omar:1.4.9#hinc robore corporibus animisque sumpto iam non feras tantum subsistere, sed in latrones praeda onustos impetus facere pastoribusque rapta dividere et cum his crescente in dies grege iuvenum seria ac iocos celebrare.
"""
val corpus = Corpus(cex)
```
