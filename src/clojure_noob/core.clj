;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.pprint :as p])
  (:require [clojure-noob.interest :as i])
  (:require [clojure-noob.date_lib :as date-lib])
  (:require [clojure-noob.utils :as utils])
  (:require [clojure-noob.schedule :as schedule]))


(def header->keys {:payment-num              "Payment Number"
                   :amount-towards-interest  "Amount towards interest"
                   :amount-towards-principle "Amount towards principle"
                   :remaining-amount         "Remaining Amount" })

(defn -main
  "Takes in loan information and creates an amorization schedule"
  [& args]

  (println "Please type in loan amount")
  (def loan-amount (utils/str->int (read-line)))

  (println "Please type in interest rate as a percentage")
  (def interest-rate (i/amorization-interest-rate (utils/str->int (read-line)) 12))

  (println "Please type in the loan term in years")
  (def loan-term (utils/str->int (read-line)))

  (def monthly-payment (schedule/monthly-amorization-payment-amount loan-amount interest-rate (* 12 loan-term)))
  (def schedule (utils/to-csv (schedule/create-amorization-schedule loan-amount monthly-payment interest-rate) header->keys))
  (println schedule)
)
