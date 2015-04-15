;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.string :as s]))

(defn now
      "Returns the current epoch time"
      []
      (quot (.getTime (java.util.Date.)) 1000))


(defn parse
  "Convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       (s/split string #"\n")))


(defn simple-interest "calculate simple interest and total amount on a principle for a given rate and time"
    [principle rate period]
    (let [si (/ (* principle rate period) 100.0)
          total (+ principle si)]
      {:interest si :total total :principle principle}));


(defn compound-interest "calculate compound intest and total amount.  Assuming compounding per year"
    [principle nominal-interest-rate times-compounded-per-year period]
    (let [total-amount (* principle (Math/pow (+ 1 (/ (/ nominal-interest-rate 100) times-compounded-per-year)) (* times-compounded-per-year period)))
          interest (- total-amount principle)]
    {:total total-amount :interest interest :principle principle :loan-length period}));


(defn make-payment-schedule "From the given payment information, starting date of loan, loan length, it will generate a monthly payment schedule"
    [principle interest-rate times-compounded-per-year loan-length]
    (let [payment-info           (compound-interest principle interest-rate times-compounded-per-year loan-length)
          number-of-payments     (* loan-length times-compounded-per-year)]
      number-of-payments));

(defn create-amorization-schedule [] )

(defn monthly-amorization-payment "Calculates the monthly payment"
  [interest-rate initial-amount number-of-payments]
  (let [montly-payment (/ (* interest-rate initial-amount (Math/pow (+ 1 interest-rate) number-of-payments)) (- (Math/pow (+ 1 interest-rate) number-of-payments) 1)]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

;;  (println (compound-interest 10769.93 5 12 5))
;;  (println (make-payment-schedule 10769.93 5 12 5 (quot (System/currentTimeMillis) 1000)))

;;  (println (make-payment-schedule 10769.93 5 12 5))

;;    (println (simple-interest 10769.93 5 5))

;;    (println (/ 2692.48 60)) ;;44.87
;;    (println (/ 10769.93 60)) ;;179.50

;;    (println (+ (/ 13462.41 60)))
    (println (/ (* 10769.93 (+ 1 (* (/ 5 100) 5))) 60) )

  )
