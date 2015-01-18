(ns clojure-noob.core
  (:gen-class))

;;If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
;;Find the sum of all the multiples of 3 or 5 below 1000.

(defn multiples-of-3-or-5
  [number-in-range]
  (if (or (= 0 (mod number-in-range 3)) (= 0 (mod number-in-range 5)))
    true
    false))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (println (reduce + (filter multiples-of-3-or-5 (range 0 1000)))))
