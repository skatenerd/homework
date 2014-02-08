(ns homework.core)

(defn find-node [predicate tree]
    (if (predicate tree)
      tree
      (if tree
        (first (map #(find-node predicate %) (:children tree))))))

(defn find-all [predicate get-children tree]
  #{})

(defn find-node-custom [predicate get-children tree]
  ;you will probably say something like:
  ;(let [children (get-children tree)])
  [])


(defn find-node-with-path [target-content-sequence get-contents get-children tree])
(defn find-all-nodes-with-path [target-content-sequence get-contents get-children tree])
