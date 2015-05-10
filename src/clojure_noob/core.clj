(ns clojure-noob.core)

  (defn split-by-space
    [x]
    (clojure.string/split x #"\s"))

  (defn to-sentence
    "transforms a collection of strings to a sentence"
    [coll]
    (clojure.string/join " " coll))

  (defn -main
    ""
    [& args]

     (println "Place type in name of file")
     (def file (read-line))

    ;;Sample code to read in test cases:
    ;;Open the file passed as the first command line argument
    ;;(with-open [rdr (clojure.java.io/reader (first *command-line-args*))]
    (with-open [rdr (clojure.java.io/reader file)]
    ; Read each line ignoring empty ones
    (doseq [line (remove empty? (line-seq rdr))]
      (println (to-sentence (rseq (split-by-space line)))))))
