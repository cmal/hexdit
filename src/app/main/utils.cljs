(ns app.main.utils)

(defn index-of [func coll]
  (some (fn [[idx item]]
          (if (func item) idx))
        (map-indexed vector coll)))

