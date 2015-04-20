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


(def header->keys {:amount-towards-interest  "Amount towards interest"
                        :amount-towards-principle "Amount towards principle"
                        :remaining-amount         "Remaining Amount" })

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
  [row]
  (str (s/join ", " row) "\n"))


(defn to-csv "converts a vector of maps into a comma seperated string"
  [data]

  (let [map-keys     (keys (first data))
        data-values  (map vals data)
        body   (apply str (map to-csv-string data-values))
        header (str (s/join ", " (map header->keys map-keys)))
        combined (str header "\n" body)]
    combined))


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
