(ns homework.core)

(declare find-node-custom)

(defn find-node [predicate tree]
  (find-node-custom predicate #(:children %) tree))

(defn find-all [predicate get-children tree]
  (let [new-finds (if (predicate tree)
                    #{tree}
                    #{})]
    (reduce
      clojure.set/union
      new-finds
      (map #(find-all predicate %) (get-children tree)))))

(defn find-node-custom [predicate get-children tree]
  (if (predicate tree)
    tree
    (some
      #(find-node-custom predicate get-children %)
      (get-children tree))))


(defn find-node-with-path [target-content-sequence get-contents get-children tree])
(defn find-all-nodes-with-path [target-content-sequence get-contents get-children tree])
