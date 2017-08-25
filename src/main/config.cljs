(ns main.config)

(def fs       (js/require "fs"))
(def path     (js/require "path"))
(def electron (js/require "electron"))
(def app      (.-app electron))
(def nconf    (js/require "nconf"))

(enable-console-print!)

(def app-config-path
  (.join path (.getPath app "home") 
              ".hexdit.json"))

(def app-config
  (.file nconf (clj->js {:file app-config-path})))

(defn set-config [field value]
  (.set app-config field value)
  (.save app-config))

(defn get-config [field]
  (.load app-config)
  (.get app-config field))

