(ns be.utils-test
  (:require [clojure.test :refer :all]
            [be.utils :refer :all]))

(deftest vals-map-test
  (is (= (vals-map inc {:a 1 :b 2}) {:a 2 :b 3}))
  (is (= (vals-map (partial * 10) {:a 1 :b 2}) {:a 10 :b 20})))

(deftest scramble-test
  (is (scramble? "rekqodlw" "world"))
  (is (scramble? "cedewaraaossoqqyt" "codewars"))
  (is (not (scramble? "katas" "steak"))))
