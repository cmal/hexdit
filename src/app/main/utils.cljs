(ns app.main.utils
  (:require [cljs-uuid-utils.core :as uuid]))

(defn index-of [func coll]
  (some (fn [[idx item]]
          (if (func item) idx))
        (map-indexed vector coll)))

(defn make-uuid []
  (uuid/uuid-string (uuid/make-random-uuid)))

