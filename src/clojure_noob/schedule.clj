(ns clojure-noob.schedule
  (:require [clojure-noob.interest :as i])
  (:require [clojure-noob.macros :as macros]))

(defn make-payment-schedule "From the given payment information, starting date of loan, loan length, it will generate a monthly payment schedule"
    [principle interest-rate times-compounded-per-year loan-length]
    (let [payment-info           (i/compound-interest principle interest-rate times-compounded-per-year loan-length)
          number-of-payments     (* loan-length times-compounded-per-year)]
      number-of-payments));

(defn create-amorization-schedule "returns a map of the break down of each payment. This breakdown will include the amount remaining on principle and how much of your payment went towards interest"
    [loan-amount monthly-payment interest]
    (loop [amount   loan-amount
           schedule []]
      (if (< amount 0)
        schedule
        (let [breakdown     (i/interest-principle-breakdown monthly-payment interest amount)
              new-amount    (get breakdown :remaining-amount)
              new-schedule  (conj schedule breakdown)]
          (recur new-amount new-schedule)))));

(defn monthly-amorization-payment-amount "Calculates the monthly payment"
  [initial-amount interest-rate number-of-payments]
  (let [monthly-payment (macros/r-infix ((interest-rate * initial-amount * ((1 + interest-rate) Math/pow number-of-payments))
                                        / (((1 + interest-rate) Math/pow number-of-payments) - 1)))]
    monthly-payment));
