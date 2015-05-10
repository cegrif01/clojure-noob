;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  (:require [clojure.pprint :as p])
  (:require [clojure.string :as str]))

  (defn split-by-space
    [x]
    (str/split x #"\s"))

  (defn split-up-words
    [line]
    (str/split line #"\n"))

;;     (def s "Hello World\nHello CodeEval")
;;   (def split-up-words (clojure.string/split s #"\n"))

;; split-up-words



;; (split-by-space split-up-words)


(defn -main
  "Takes in loan information and creates an amorization schedule"
  [& args]

   (println "Place type in name of file")
   (def file (read-line))

  ;;   ; Sample code to read in test cases:
  ;;   ; Open the file passed as the first command line argument
  (with-open [rdr (clojure.java.io/reader file)]
  ; Read each line ignoring empty ones
  (doseq [line (remove empty? (line-seq rdr))]
    ;;(println (vector line))))
    (println (first (split-by-space line)))))


;;     (def lines '(["Hot" "Damn" "Cheese"] ["Hello" "CodeEval"]))
;;     (println lines)
;;    (println (map #(sort-by last %) lines))

;; (apply str (map #(str (s/join ", " %) "\n") data-values))
)
