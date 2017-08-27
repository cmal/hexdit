(ns main.config)

(def fs       (js/require "fs"))
(def path     (js/require "path"))
(def electron (js/require "electron"))
(def app      (.-app electron))
(def nconf    (js/require "nconf"))

(enable-console-print!)

(def config-path
  (.join path (.getPath app "home") 
              ".hexdit.json"))

(def config
  (.file nconf (clj->js {:file config-path})))

(defn set-config [field value]
  (.set config field value)
  (.save config))

(defn get-config [field]
  (.load config)
  (.get config field))

