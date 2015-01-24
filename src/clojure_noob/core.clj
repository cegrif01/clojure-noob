;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.string :as s]))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(def filename "suspects.csv")

(def headers->keywords {"Name" :name
                        "Glitter Index" :glitter-index})

(defn fetch-by-key
  [recs key]
  (map (fn[rec]
         (get rec key))
   recs))

(defn str->int
  [str]
  (Integer. str))

(defn parse
  "convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       (s/split string #"\n")))

(defn prepend
  [recs rec-to-add]
  (conj (into () recs) rec-to-add))


;; CSV is all text, but we're storing numeric data. We want to convert
;; it back to actual numbers.
(def conversions {:name identity
                  :glitter-index str->int})

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))


(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (let [;; headers becomes the seq (:name :glitter-index)
        headers (map #(get headers->keywords %) (first rows))
        ;; unmapped-rows becomes the seq
        ;; (["Edward Cullen" "10"] ["Bella Swan" "0"] ...)
        unmapped-rows (rest rows)]

    ;; Now let's return a seq of {:name "X" :glitter-index 10}
    (map (fn [unmapped-row]
              ;; We're going to use map to associated each header with its
              ;; column.  Since map returns a seq, we use "into" to convert
              ;; it into a map
              (into {}
                    ;; notice we're passing multiple collections to map
                    (map (fn [header column]
                           ;; associate the header with the converted column
                           [header ((get conversions header) column)])
                         headers
                         unmapped-row)))
     unmapped-rows)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;;(println (fetch-by-key (glitter-filter 3 (mapify (parse (slurp filename)))) :name))
  ;;(println (fetch-by-key [{:name "Charles" :glitter-index 5} {:name "Mandy" :glitter-index 10}] :name))
  (println (prepend [{:name "Charles" :glitter-index 5} {:name "Mandy" :glitter-index 10}] {:name "Gilbert" :glitter-index 12}))
  )
