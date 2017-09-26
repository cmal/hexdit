(ns main.ipc
  (:require [main.config :as config]
            [main.hexo :as hexo]
            [main.common :refer [blog-window-options default-window-options]]))

(def electron (js/require "electron"))
(def browser-window (.-BrowserWindow electron))
(def ipcMain (.-ipcMain electron))

(defn defipc [ipcname ipcfn]
  (.on ipcMain ipcname ipcfn))

(defipc "get-blog-list"
  (fn [event _]
    (let [empty-list (clj->js [])
          config (config/get-config "blog-list")
          blog-list (if-not (= config nil) config empty-list)]
      (if (= config nil)
        (config/set-config "blog-list" empty-list))
      (aset event "returnValue" blog-list))))

(defipc "open-blog"
  (fn [_ _]
    (let [main-window (.getFocusedWindow browser-window)]
      (.setSize main-window
                (:width blog-window-options)
                (:height blog-window-options))
      (.setResizable main-window
                     (:resizable blog-window-options))
      (.center main-window))))

(defipc "close-blog"
  (fn [_ _]
    (let [main-window (.getFocusedWindow browser-window)]
      (.setSize main-window
                (:width default-window-options)
                (:height default-window-options))
      (.setResizable main-window
                     (:resizable default-window-options))
      (.center main-window))))

(defipc "remove-blog"
  (fn [event idx]
    (let [blog-list (js->clj (config/get-config "blog-list"))
          new-blog-list (clj->js (concat (subvec blog-list 0 idx)
                                         (subvec blog-list (inc idx))))]
      (config/set-config "blog-list" new-blog-list)
      (aset event "returnValue" new-blog-list))))

(defipc "add-blog"
  (fn [event info]
    (let [blog-list (js->clj (config/get-config "blog-list"))
          new-blog (js->clj info)
          new-blog-list (clj->js (conj blog-list new-blog))]
      (if (hexo/check-pkg (get new-blog "path"))
        (do
          (config/set-config "blog-list" new-blog-list)
          (aset event "returnValue" new-blog-list))
        (aset event "returnValue" (clj->js blog-list))))))

