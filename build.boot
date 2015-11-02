(set-env!
 :dependencies '[[hiccup "1.0.5"]])

(task-options!
 pom {:project 'dgellow/boot-hiccup
      :version "0.1.0-SNAPSHOT"
      :description "Boot task to compile Hiccup template functions"
      :url "https://github.com/dgellow/boot-hiccup"
      :scm {:url "https://github.com/dgellow/boot-hiccup.git"}
      :license {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})
