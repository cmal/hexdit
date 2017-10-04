(ns app.renderer.ipc)

(def electron (js/require "electron"))
(def ipc-renderer (.-ipcRenderer electron))

(defn get-bloggers []
  (js->clj (.sendSync ipc-renderer "get-bloggers")))
