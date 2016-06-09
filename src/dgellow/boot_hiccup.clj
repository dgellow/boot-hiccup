(ns dgellow.boot-hiccup
  (:require [clojure.java.io :as io]
            [hiccup.core :refer [html]]
            [boot.core :as core :refer [deftask]]
            [boot.util :as util]))

(defn- log
  ([fn-name msg] (log fn-name msg nil))
  ([fn-name msg lvl]
   ((if (= :warn lvl) util/warn util/info)
    (format "[%s] %s\n" fn-name msg))))

(def ^{:private true} log-hiccup (partial log "hiccup"))

(defn- hiccup->file [output-dir file f]
  (let [output-file (io/file output-dir file)
        f-resolved (resolve f)]
    (if-not f-resolved
      (log-hiccup (format "Cannot resolve symbol %s" f) :warn)
      (let [f-result (f-resolved)
            content (if (string? f-result)
                      f-result
                      (html f-result))]
        (log-hiccup (format "• %s -> %s" (str f) file))
        (doto output-file
          io/make-parents
          (spit content))))))

(deftask hiccup
  "Compile Hiccup templates"
  [f files FILENAME=TEMPLATE-FN #{[str sym]} "A map pairing filenames and template functions"]
  (let [output-dir (core/tmp-dir!)]
    (fn middleware [next-handler]
      (fn handler [fileset]
        (core/empty-dir! output-dir)
        (if-not files
          (do
            (log-hiccup "Missing :files param, do nothing." :warn)
            (next-handler fileset))
          (do
            (log-hiccup "Hiccup templates")
            (doseq [[file f] (seq files)]
              (do
                (let [ns (symbol (namespace f))]
                  (require ns)
                  (hiccup->file output-dir file f))))
            (-> fileset
               (core/add-resource output-dir)
               core/commit!
               next-handler)))))))
