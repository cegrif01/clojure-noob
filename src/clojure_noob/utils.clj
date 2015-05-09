(ns clojure-noob.utils
    (:require [clojure.java.io :as io])
    (:require [clojure.string :as s])
  )

;;Here is an example of Java interop
;;Integer is a call to Java's integer class
;;the . is a constructor call.
;;So we are passing a string into the constrcutor
;;of Java's Integer class.
(defn str->int
  [str]
  (Integer. str))


(defn to-csv "converts a vector of maps into a comma seperated string. The header->keys must be the map key names"
  [data header->keys]

  (let [map-keys     (keys (first data))
        data-values  (map vals data)
        body         (apply str (map #(str (s/join ", " %) "\n") data-values))
        header       (str (s/join ", " (map header->keys map-keys)))
        combined     (str header "\n" body)]
    combined))

