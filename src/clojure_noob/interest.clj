(ns clojure-noob.interest)

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


(defn amortization-interest-rate
  [interest-rate number-of-times-per-year]
  (/ (/ interest-rate number-of-times-per-year) 100))
