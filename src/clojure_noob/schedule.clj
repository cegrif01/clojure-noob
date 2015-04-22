(ns clojure-noob.schedule
  (:require [clojure-noob.interest :as i])
  (:require [clojure-noob.macros :as macros]))

(defn interest-principle-breakdown "Determines how much of a monthly payment goes towards interest and towards the actual loan amount"
  [monthly-payment interest-rate loan-balance]
  (let [interest          (float (* loan-balance interest-rate))
        principle         (float (- monthly-payment interest))
        remaining-amount  (float (- loan-balance principle))]
    {:amount-towards-interest interest, :amount-towards-principle principle, :remaining-amount remaining-amount}
));

(defn create-amortization-schedule "returns a map of the break down of each payment. This breakdown will include the amount remaining on principle and how much of your payment went towards interest"
    [loan-amount monthly-payment interest]
    (loop [amount       loan-amount
           schedule     []
           payment-num  1]
      (if (< amount 0)
        schedule
        (let [breakdown     (assoc (interest-principle-breakdown monthly-payment interest amount) :payment-num payment-num)
              new-amount    (breakdown :remaining-amount)
              new-schedule  (conj schedule breakdown)]
          (recur new-amount new-schedule (inc payment-num))))));

(defn recurring-amortization-payment "Calculates the recurring payment amount"
  [initial-amount amort-interest-rate number-of-payments]
  (let [monthly-payment (macros/r-infix ((amort-interest-rate * initial-amount * ((1 + amort-interest-rate) Math/pow number-of-payments))
                                        / (((1 + amort-interest-rate) Math/pow number-of-payments) - 1)))]
    monthly-payment));
