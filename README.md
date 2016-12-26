# boot-hiccup [![Circle CI](https://circleci.com/gh/dgellow/boot-hiccup.svg?style=svg)](https://circleci.com/gh/dgellow/boot-hiccup)

[](dependency)
```clojure
[dgellow/boot-hiccup "1.0.0"] ;; latest release
```
[](/dependency)

Compile [Hiccup](https://github.com/weavejester/hiccup) template functions into HTML files.

## Usage

In your `build.boot`, add `dgellow/boot-hiccup` as a dependency then `require` the task from the namespace `dgellow.boot-hiccup`:

```clojure
(set-env! :dependencies '[[dgellow/boot-hiccup "X.Y.Z"]])
(require '[dgellow.boot-hiccup :refer [hiccup]])
```

### From `build.boot`, in a task

```clojure
(deftask run-hiccup []
  (comp (hiccup :files {"index.html" 'foo.templates/index
                        "tests.html" 'foo.templates/tests})))
```

### From the terminal (cli)

```
boot hiccup -f index.html=foo.templates/index \
            -f tests.html=foo.templates/tests
```

### With `watch`

In the spirit of tightly focused composable tasks, the `hiccup` task doesn't do anything to reload code.
So, if you want to use `hiccup` with `watch`, you should also use the `refresh` task from [boot-refresh] like so:

```clojure
(require '[samestep.boot-refresh :refer [refresh]])

(deftask watch-hiccup []
  (comp
    (watch)
    (refresh)
    (hiccup :files {"index.html" 'foo.templates/index
                    "tests.html" 'foo.templates/tests})))
```

[boot-refresh]: https://github.com/samestep/boot-refresh
