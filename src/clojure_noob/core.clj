;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.string :as s]))

(def filename "suspects.csv")

(def headers->keywords {"Name" :name "Glitter Index" :glitter-index})

(defn str->int
  [str]
  (Integer. str))

(defn parse
  "convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       #(s/split string #" ")))

;; CSV is all text, but we're storing numeric data. We want to convert
;; it back to actual numbers.
(def conversions {:name identity
                  :glitter-index str->int})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (slurp filename)))
