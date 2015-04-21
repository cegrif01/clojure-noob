(ns clojure-noob.utils
    (:require [clojure.java.io :as io])
    (:require [clojure.string :as s])
  )

(defn parse
  "Convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       (s/split string #"\n")))

(defn to-csv "converts a vector of maps into a comma seperated string. The header->keys must be the map key names"
  [data header->keys]

  (let [map-keys     (keys (first data))
        data-values  (map vals data)
        body         (apply str (map #(str (s/join ", " %) "\n") data-values))
        header       (str (s/join ", " (map header->keys map-keys)))
        combined     (str header "\n" body)]
    combined))

(defn write-file [file-name data]
  (with-open [w (io/writer file-name :append true)]
    (.write w data)))


(defn read-file
  "Read a resource into a string"
  [path]
  (-> path io/file slurp))
