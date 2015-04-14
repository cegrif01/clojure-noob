;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.string :as s]))


(defn simple-interest "calculate simple interest and total amount on a principle for a given rate and time"
    [principle rate period]
    (let [si (/ (* principle rate period) 100.0)
          amount (+ principle si)]
      {:interest si :amount amount}));

(defn compound-interest "calculate compound intest and total amount.  Assuming compounding per year"
    [principle nominal-interest-rate times-compounded period]
    (let [total-amount (* principle (Math/pow (+ 1 (/ (/ nominal-interest-rate 100) times-compounded)) (* times-compounded period)))
          interest (- total-amount principle)]
    {:total total-amount :interest interest}));



(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ;;(println (simple-interest 10000 4 5))
  (println (compound-interest 1500 4.3 4 6))



  )
