(ns clojure_noob.core)

  (defn split-by-space
    [coll]
    (clojure.string/split coll #"\s"))

  (defn to-sentence
    "transforms a collection of strings to a sentence"
    [coll]
    (clojure.string/join " " coll))

  (defn -main
    "Will take in a file as an arguemnt.  Reads through each line in file and reverses the sentence"
    [& args]

    ;;Sample code to read in test cases:
    ;;Open the file passed as the first command line argument
    (with-open [rdr (clojure.java.io/reader (first *command-line-args*))]
    ; Read each line ignoring empty ones
    (doseq [line (remove empty? (line-seq rdr))]
      (println (to-sentence (rseq (split-by-space line)))))))
