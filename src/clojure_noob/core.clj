;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.string :as s])
  (:require [clojure.pprint :as p])
  (:require [clojure-noob.interest :as i])
  (:require [clojure-noob.DateLib :as date-lib])
  (:require [clojure-noob.macros :as macros])
  (:require [clojure.java.io :as io]))


(defn parse
  "Convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       (s/split string #"\n")))

(def headers->keywords {"Amount towards interest" :amount-towards-interest
                        "Amount towards principle" :amount-towards-principle
                        "Remaining Amount" :remaining-amount})


(defn make-payment-schedule "From the given payment information, starting date of loan, loan length, it will generate a monthly payment schedule"
    [principle interest-rate times-compounded-per-year loan-length]
    (let [payment-info           (i/compound-interest principle interest-rate times-compounded-per-year loan-length)
          number-of-payments     (* loan-length times-compounded-per-year)]
      number-of-payments));


(defn monthly-amorization-payment-amount "Calculates the monthly payment"
  [initial-amount interest-rate number-of-payments]
  (let [monthly-payment (macros/r-infix ((interest-rate * initial-amount * ((1 + interest-rate) Math/pow number-of-payments))
                                        / (((1 + interest-rate) Math/pow number-of-payments) - 1)))]
    monthly-payment));


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

(defn to-csv-string
  [entry]

  (let
    [headers      (keys entry)
     data-entry   (map  entry headers)
     csv          (str (s/join ", " data-entry) "\n")]
    csv))

(defn to-csv "converts a vector of maps into a comma seperated string"
  [data]

  (let [body   (apply str (map to-csv-string data))
        header (str (s/join ", " (keys (first data))))
        combined (str header "\n" body)]
combined)
  )


(defn read-file
  "Read a resource into a string"
  [path]
  (-> path io/file slurp))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (def interest (i/amorization-interest-rate 5 12))
  (def monthly-payment (monthly-amorization-payment-amount 10769.93 interest 60))
  (print (to-csv (create-amorization-schedule 10769.93 monthly-payment interest)))

  )
