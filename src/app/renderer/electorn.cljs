(ns app.renderer.electorn)

(def electron (js/require "electron"))
(def remote (.-remote electron))

(def dialog (.-dialog remote))
(def tray (.-Tray remote))

(def ipc-renderer (.-ipcRenderer electron))
(defn ipc-send-sync [event & [params]]
  (.sendSync ipc-renderer (name event) params))

(defn ipc-send [event & [params]]
  (.send ipc-renderer (name event) params))
