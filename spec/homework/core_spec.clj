(ns homework.core-spec
  (:require [speclj.core :refer :all]
            [homework.core :refer :all]))

(defn is-the-root? [node]
  (= (:contents node) "ROOT"))

(describe "dfs"
  (with tree {:contents "ROOT"
              :children [{:contents "FIRST CHILD"
                          :children []}
                         {:contents "SECOND CHILD"
                          :children [{:contents "GRANDCHILD" :children []}]}]})
  (it "finds the root node"
    (should=
      @tree
      (find-node
        is-the-root?;condition for success, "find me the root node"
        @tree)));where to look

  (it "returns nil when the target is absent"
    (should=
      nil
      (find-node
        (fn [_] false);never happy!
        @tree)))

  (it "finds the second child of the root"
    (should=
      {:contents "SECOND CHILD"
       :children [{:contents "GRANDCHILD" :children []}]}
      (find-node
        #(= (:contents %) "SECOND CHILD")
        @tree)))

  (it "Finds the first child of the root"
    (should=
      {:contents "FIRST CHILD"
       :children []}
      (find-node
        #(= (:contents %) "FIRST CHILD")
        @tree))))

;note: 'rest' is a function!
(describe "DFS with Custom child-finder"
  (with tree [2
               [7 2]
               [9
                [4 [11] [19]]]
               [22
                [5 [81]]]])

  (it "Finds the grandchild with contents of '5'"
    (should=
      [5 [81]]
      (find-node-custom #(= (first %) 5) rest @tree))))
