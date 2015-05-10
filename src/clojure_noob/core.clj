;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.pprint :as p])
  (:require [clojure.string :as str]))

  (defn reverse-word-order
    [line]
    (str/split line #"\n")
    )

;;     (def s "Hello World\nHello CodeEval")
;;   (def split-up-words (clojure.string/split s #"\n"))

;; split-up-words

;; (defn split-by-space
;;   [x]
;;   (map #(clojure.string/split % #"\s") x))

;; (split-by-space split-up-words)




(defn -main
  "Takes in loan information and creates an amorization schedule"
  [& args]

;;    (println "Place type in name of file")
;;    (def file (read-line))

;;   ;;   ; Sample code to read in test cases:
;;   ;;   ; Open the file passed as the first command line argument
;;   (with-open [rdr (clojure.java.io/reader file)]
;;   ; Read each line ignoring empty ones
;;   (doseq [line (remove empty? (line-seq rdr))]
;;     (println (reverse-word-order line))))

   (def lines '(["Hot" "Damn" "Cheese"] ["Hello" "CodeEval"]))

   (println (map #(sort-by last %) lines))

;; (apply str (map #(str (s/join ", " %) "\n") data-values))
)
