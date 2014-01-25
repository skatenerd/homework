(ns homework.core)

(defn find-node [predicate tree]
    (if (predicate tree)
      tree
      (if tree
        (first (map #(find-node predicate %) (:children tree))))))

(defn find-node-custom [predicate get-children tree]
  ;you will probably say something like:
  ;(let [children (get-children tree)])
  [])


(defn find-node-with-path [path-predicate get-children tree])
;CONTRACT
;if it's in the subree
  ;return it
;if it's not in the subtee
  ;return nil


;IDEA:
; if predicate tree
;  tree
;  else
; get the children of the tree and iterate through them, checking each one
