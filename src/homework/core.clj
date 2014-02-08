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
      (map #(find-all predicate get-children %) (get-children tree)))))

(defn find-node-custom [predicate get-children tree]
  (if (predicate tree)
    tree
    (some
      #(find-node-custom predicate get-children %)
      (get-children tree))))

(defn- subsequence? [shorter longer]
  (= (take (count shorter) longer) shorter))

(defn find-with-path [target-content-sequence get-contents get-children tree traversal-algorithm]
  (let [get-children-metagraph (fn [node]
                                 (vec (filter
                                        #(subsequence? (:path %) target-content-sequence)
                                        (map
                                          (fn [child] {:node child :path (conj (vec (:path node)) (get-contents child))})
                                          (get-children (:node node))))))
        metagraph-predicate #(= target-content-sequence (:path %))]
    (traversal-algorithm metagraph-predicate get-children-metagraph {:node tree :path [(get-contents tree)]})))

(defn find-node-with-path [target-content-sequence get-contents get-children tree]
  (:node (find-with-path target-content-sequence get-contents get-children tree find-node-custom)))

(defn find-all-nodes-with-path [target-content-sequence get-contents get-children tree]
  (map :node (find-with-path target-content-sequence get-contents get-children tree find-all)))
