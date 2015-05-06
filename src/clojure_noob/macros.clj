(ns clojure-noob.macros)


(defmacro r-infix [form]
  (cond (not (seq? form))
         form
        (= 1 (count form))
        `(r-infix ~(first form))
        :else
        (let [operator  (second form)
              first-arg (first form)
              others (rest (rest form))]
             `(~operator
               (r-infix ~first-arg)
               (r-infix ~others)))))
