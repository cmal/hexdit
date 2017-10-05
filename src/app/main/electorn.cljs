(ns app.main.electorn)

(def electron (js/require "electron"))
(def app (.-app electron))
(def browser-window (.-BrowserWindow electron))

(def ipc-main (.-ipcMain electron))
;; register ipc event
(defn reg-ipc-event [ipcname ipcfn]
  (.on ipc-main (name ipcname) ipcfn))
