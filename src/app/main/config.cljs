(ns app.main.config
  (:require [app.main.electorn :refer [app]]))

(def fs       (js/require "fs"))
(def path     (js/require "path"))
(def nconf    (js/require "nconf"))

(def config-path
  (.join path
         (.getPath app "userData")
         "config.json"))

(def config
  (.file nconf (clj->js {:file config-path})))

(defn set-field [field value]
  (.set config field value)
  (.save config))

(defn get-field [field]
  (.load config)
  (.get config field))
