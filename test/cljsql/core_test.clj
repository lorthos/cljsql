(ns cljsql.core-test
  (:require [clojure.test :refer :all]
            [cljsql.core :refer :all]))

(defn sql [stmt] (parse-sql stmt))
(defn sql! [stmt] (parse-sql stmt :unhide :content))

(deftest parsing-test
  (testing "select *"
    (is (= (sql "select * from table ")
           [:SQL
            [:FIELDS
             "* "]]
           ))
    (is (= (sql "select * from table where x=y")
           [:SQL
            [:FIELDS
             "* "]
            [:WHERE_CLAUSE
             [:PREDICATE
              "x=y"]]]
           ))
    ;todo parse predicates properly
    (is (= (sql "select * from table where x=y and 1=1")
           nil
           ))
    ))
