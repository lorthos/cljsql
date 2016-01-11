(ns cljsql.core-test
  (:require [clojure.test :refer :all]
            [cljsql.core :refer :all])
  (:import (org.apache.calcite.sql SqlSelect)))

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
    (is (.hasWhere ^SqlSelect (parse "select * from table1 where x=y and 1=1")))
    ))
