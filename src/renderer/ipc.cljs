(ns renderer.ipc)

(def electron (js/require "electron"))
(def ipcRenderer (.-ipcRenderer electron))

(defn open-blog []
  (.send ipcRenderer "open-blog"))

(defn close-blog []
  (.send ipcRenderer "close-blog"))

(defn get-blog-list []
  (js->clj (.sendSync ipcRenderer "get-blog-list")))

(defn remove-blog [idx]
  (js->clj (.sendSync ipcRenderer "remove-blog" idx)))

(defn add-blog [blog]
  (js->clj (.sendSync ipcRenderer "add-blog" blog)))

(defn preview-blog [blog]
  (.send ipcRenderer "preview-blog" blog))

