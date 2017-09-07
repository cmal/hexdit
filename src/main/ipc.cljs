(ns main.ipc
  (:require [main.config :refer [get-config set-config]]
            [main.common :refer [app-window-options]]))

(def electron (js/require "electron"))
(def browser-window (.-BrowserWindow electron))
(def ipcMain (.-ipcMain electron))

(defn defipc [ipcname ipcfn]
  (.on ipcMain ipcname ipcfn))

(defipc "get-blog-list"
  (fn [event _]
    (let [config (get-config "blog-list")
         blog-list (if-not (= config nil) config [])]
     (if (= config nil)
       (set-config "blog-list" (clj->js [])))
     (aset event "returnValue" blog-list))))

(defipc "open-blog"
  (fn [event _]
    (let [main-window (.getFocusedWindow browser-window)]
      (.setSize main-window
                 (.-width app-window-options)
                 (.-height app-window-options))
      (.center main-window))))

