(ns be.utils)

(defn vals-map [f x]
  (into {} (for [[k v] x] [k (f v)])))

(defn scramble? [s1 s2]
  (every? (comp nat-int? second)
          (merge-with +
                      (frequencies s1)
                      (vals-map (partial * -1) (frequencies s2)))))
