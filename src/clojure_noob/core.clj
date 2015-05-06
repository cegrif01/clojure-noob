;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.pprint :as p])
  (:require [clojure-noob.date_lib :as date-lib])
  (:require [clojure-noob.utils :as utils])
  (:require [clojure-noob.schedule :as schedule]))


(def header->keys {:payment-num              "Payment Number"
                   :amount-towards-interest  "Amount towards interest"
                   :amount-towards-principle "Amount towards principle"
                   :remaining-amount         "Remaining Amount" })

(defn generate-schedule
  [loan-amount interest-rate loan-term number-of-payments-per-month csv?]
  (let [num-of-pmts-per-year       (* number-of-payments-per-month 12)
        amort-interest-rate        (schedule/amortization-interest-rate interest-rate num-of-pmts-per-year)
        recurring-amount           (schedule/recurring-amortization-payment loan-amount amort-interest-rate (* num-of-pmts-per-year loan-term))
        schedule                   (schedule/create-amortization-schedule loan-amount recurring-amount amort-interest-rate)
        schedule->to-csv           (utils/to-csv schedule header->keys)]
        (if csv?
          schedule->to-csv
          schedule)
    ))

(defn -main
  "Takes in loan information and creates an amorization schedule"
  [& args]

  (println "Please type in loan amount")
  (def loan-amount (utils/str->int (read-line)))

  (println "Please type in interest rate as a percentage")
  (def interest-rate (utils/str->int (read-line)))

  (println "Please type in the loan term in years")
  (def loan-term (utils/str->int (read-line)))

  (println "Number of payments per month")
  (def number-of-payments-per-month (utils/str->int (read-line)))

  (println (generate-schedule loan-amount interest-rate loan-term number-of-payments-per-month true))
)
