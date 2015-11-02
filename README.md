# boot-hiccup [![Circle CI](https://circleci.com/gh/dgellow/boot-hiccup.svg?style=svg)](https://circleci.com/gh/dgellow/boot-hiccup)

[](dependency)
```
[dgellow/boot-hiccup "0.1.0-SNAPSHOT"]
```
[](/dependency)

Compile [Hiccup](https://github.com/weavejester/hiccup) template functions into HTML files.

## Usage

In your `build.boot`, add `[dgellow/boot-hiccup "0.1.0-SNAPSHOT"]` as a dependency then require the task from `dgellow.boot-hiccup`.

## Exemple

From a boot task:

```
(set-env!
 :source-paths #{"src"}
 :dependencies '[[dgellow/boot-hiccup "0.1.0-SNAPSHOT" :scope "test"]
                 [org.clojure/clojure "1.7.0"]])

(require
 '[dgellow.boot-hiccup :refer [hiccup]])

(deftask run-hiccup []
  (comp (hiccup :namespaces #{foo.templates}
                :files {"index.html" 'foo.templates/index
                        "tests.html" 'foo.templates/tests})))
```

From CLI:

```
boot hiccup -ns foo.templates.core \
            -f index.html=foo.templates/index \
            -f tests.html=foo.templates/tests
```
