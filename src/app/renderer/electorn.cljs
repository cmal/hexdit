(ns app.renderer.electorn)

(def electron (js/require "electron"))
(def dialog (.-dialog (.-remote electron)))
(def ipc-renderer (.-ipcRenderer electron))
