# Rex Flotsam: Beach Cop

You are a detective on the beach trying to find the owner of a washed-up severed foot. But soon it becomes clear that things are not as they seem...

A game for the Global Game Jam 2017, built using ClojureScript, SVG and [re-frame](https://github.com/Day8/re-frame).


The game is precompiled into Javascript/HTML, so you can just open up the [index.html](resources/public/index.html) file in your browser.

A hosted version of the game is at: [mthompson.org/rex](http://mthompson.org/rex)

You will need to install [leiningen](https://leiningen.org) to build/run the game from source:

## Production Build

To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
Then open [index.html](resources/public/index.html) in your browser.

## Or run application server with Leiningen:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).
