(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def severity :mild)
  (def error-message "OH GOD! II'S A DISASTER! WE'RE ")
  (if (= severity :mild)
      (def error-message (str error-message "MIILDLY INCONVENIENCED!"))
      (def error-message (str error-message "DOOOMED!!"))

    )

  )
