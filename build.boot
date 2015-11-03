(set-env!
 :dependencies '[[hiccup "1.0.5"]
                 [adzerk/bootlaces "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.2.1-SNAPSHOT")

(bootlaces! +version+)

(task-options!
 pom {:project 'dgellow/boot-hiccup
      :version +version+
      :description "Boot task to compile Hiccup template functions"
      :url "https://github.com/dgellow/boot-hiccup"
      :scm {:url "https://github.com/dgellow/boot-hiccup"}
      :license {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})
