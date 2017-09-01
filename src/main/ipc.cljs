(ns main.ipc
  (:require [main.config :refer [get-config set-config]]))

(def electron (js/require "electron"))
(def ipcMain (.-ipcMain electron))

(defn get-blog-list []
  (.on ipcMain "get-blog-list" 
       (fn [event _]
         (let [config (get-config "blog-list")
               blog-list (if-not (= config nil) config [])]
           (if (= config nil)
             (set-config "blog-list" (clj->js [])))
           (aset event "returnValue" blog-list)))))

(defn registry-ipc []
  (get-blog-list))
