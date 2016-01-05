(ns cljsql.core
  (:require [instaparse.core :as insta]))

(def parse-sql
  (insta/parser
    "SQL                = SELECT_QUERY
     <SELECT_QUERY>     = <SELECT> FIELDS <FROM> <TABLE> WHERE_CLAUSE?  ORDER_CLAUSE?
     SELECT             = 'select '
     FIELDS             = (FIELD)+ | '* '
     <FIELD>            = #'[a-z|_|(|)]+'  <#' '+>
     FROM               = 'from '
     TABLE              = #'[a-z|_|0-9]+' <' '>
     PREDICATE          = (#'[a-z|A-Z|_| |(|)|>|<|=|!|0-9|?|\\'|%]+')
     WHERE              = 'where '
     WHERE_AND          = ' and '
     WHERE_CLAUSE       = <WHERE> PREDICATE {WHERE_AND PREDICATE}
     ORDER_STMT         = 'order by '
     ORDER_CLAUSE       = <ORDER_STMT>  (#'[a-z|A-Z|_| |>|<|=|0-9|?|\\'|%]+')
     "
    :string-ci true))
