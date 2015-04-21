(ns clojure-noob.date_lib)

(defn now
      "Returns the current epoch time"
      []
      (quot (.getTime (java.util.Date.)) 1000))
