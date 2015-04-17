(ns clojure-noob.macros)

(defmacro infix [form]
  (list (second form) (first form) (nth form 2)))
