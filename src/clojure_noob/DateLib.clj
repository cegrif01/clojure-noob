(ns clojure-noob.DateLib)

(defn now
      "Returns the current epoch time"
      []
      (quot (.getTime (java.util.Date.)) 1000))
