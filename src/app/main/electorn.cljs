(ns app.main.electorn)

(def electron (js/require "electron"))
(def app (.-app electron))
(def browser-window (.-BrowserWindow electron))
(def ipc-main (.-ipcMain electron))
