---
layout:  page
title: "Objects created by the validator"
---




## Instantiate a validator



```scala
import edu.holycross.shot.mid.validator._

val repo = EditorsRepo("resources/chantsample")

// ALSO NEED READERS AND ORTHOGRPAHY
//val midValidator = Validator(repo)
```


You can get a DSE Vector directly from a `Validator` instance:

FAKE:
```
val dseV = midValidator.dse
```
