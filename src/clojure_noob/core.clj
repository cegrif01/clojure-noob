;; In ns below, notice that "gen-class" was removed
(ns clojure-noob.core
  ;; We haven't gone over require but we will.
  (:require [clojure.pprint :as p])
  (:require [clojure-noob.interest :as i])
  (:require [clojure-noob.date_lib :as date-lib])
  (:require [clojure-noob.utils :as utils])
  (:require [clojure-noob.schedule :as schedule]))


(def header->keys {:amount-towards-interest  "Amount towards interest"
                   :amount-towards-principle "Amount towards principle"
                   :remaining-amount         "Remaining Amount" })

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ;;(def interest (i/amorization-interest-rate 5 12))
  ;;(def monthly-payment (schedule/monthly-amorization-payment-amount 10769.93 interest 60))
;;  (def schedule (utils/to-csv (schedule/create-amorization-schedule 10769.93 monthly-payment interest) header->keys))

  ;;(println schedule)
;;  (write-file "suspects.csv" schedule)

  (println "Please type in loan amount")
  (def loan-amount (utils/str->int (read-line)))

  (println "Please type in interest rate as a percentage")
  (def interest-rate (utils/str->int (read-line)))

  (println "Please type in the loan term in years")
  (def loan-term (utils/str->int (read-line)))

  (def interest-rate (i/amorization-interest-rate interest-rate 12))
;;  (def interest-rate (i/amorization-interest-rate interest-rate 12))

  (println interest-rate)

  )
