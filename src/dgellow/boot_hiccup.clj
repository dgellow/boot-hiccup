(ns dgellow.boot-hiccup
  (:require [clojure.java.io :as io]
            [hiccup.core :refer [html]]
            [boot.core :as core]
            [boot.util :refer [info]]))

(defn- hiccup->file [output-dir [filename f]]
  (let [output-file (io/file output-dir filename)
        f-resolved (resolve f)]
    (when f-resolved
      (let [f-result (f-resolved)
            content (if (string? f-result)
                      f-result
                      (html f-result))]
        (info "• %s -> %s\n" (str f) filename)
        (doto output-file
          io/make-parents
          (spit content))))))

(core/deftask hiccup
  "Compile Hiccup templates"
  [ns namespaces NS #{sym} "Namespaces containing template functions"
   f files FILENAME=TEMPLATE-FN #{[str sym]} "A map pairing filenames and template functions"]
  (let [output-dir (core/tmp-dir!)]
    (fn middleware [next-handler]
      (fn handler [fileset]
        (core/empty-dir! output-dir)
        (when files
          (info "Hiccup templates\n")
          (dorun (map require namespaces))
          (dorun (map (partial hiccup->file output-dir) files)))
        (-> fileset
           (core/add-resource output-dir)
           core/commit!
           next-handler)))))
