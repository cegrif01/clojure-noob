;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.string :as s])
  (:require [clojure.pprint :as p]))

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

(defn amorization-interest-rate
  [interest-rate number-of-times-per-year]
  (/ (/ interest-rate number-of-times-per-year) 100))

(defn monthly-amorization-payment-amount "Calculates the monthly payment"
  [initial-amount interest-rate number-of-payments]
  (let [monthly-payment (/ (* interest-rate initial-amount (Math/pow (+ 1 interest-rate) number-of-payments))
                           (- (Math/pow (+ 1 interest-rate) number-of-payments) 1))]
    monthly-payment));

(defn interest-principle-breakdown "Determines how much of a monthly payment goes towards interest and towards the actual loan amount"
  [monthly-payment interest-rate loan-balance]
  (let [interest          (float (* loan-balance interest-rate))
        principle         (float (- monthly-payment interest))
        remaining-amount  (float (- loan-balance principle))]
    {:amount-towards-interest interest, :amount-towards-principle principle, :remaining-amount remaining-amount}
));

(defn create-amorization-schedule "returns a map of the break down of each payment. This breakdown will include the amount remaining on principle and how much of your payment went towards interest"
    [loan-amount monthly-payment interest]
    (loop [amount   loan-amount
           schedule []]
      (if (< amount 0)
        schedule
        (let [breakdown     (interest-principle-breakdown monthly-payment interest amount)
              new-amount    (get breakdown :remaining-amount)
              new-schedule  (conj schedule breakdown)]
          (recur new-amount new-schedule)))));


(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (def interest (amorization-interest-rate 5 12))
  (def monthly-payment (monthly-amorization-payment-amount 10769.93 interest 60))
  ;;(println (interest-principle-breakdown monthly-payment interest 10769.93))
  (p/print-table (create-amorization-schedule 10769.93 monthly-payment interest))

)
