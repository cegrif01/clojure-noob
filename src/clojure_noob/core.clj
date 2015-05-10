;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.pprint :as p])
  (:require [clojure.string :as str])
  (:require [clojure-noob.date_lib :as date-lib])
  (:require [clojure-noob.utils :as utils])
  (:require [clojure-noob.schedule :as schedule]))

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

(defn reverse-order
  [sentence]
  (loop [first-word     (first sentence)
         rest-of-words  (rest sentence)
         rev-vector     []]

        (flatten (conj rev-vector rest-of-words first-word))
;;       (if (empty? rest-of-words)
;;         rev-vector
;;         (recur (flatten (conj rev-vector rest-of-words first-word)))))))

  ))



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

 (println (map reverse-order '(["Hot" "Damn" "Cheese"] ["Hello" "CodeEval"])))

;; (apply str (map #(str (s/join ", " %) "\n") data-values))
)
