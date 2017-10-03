(ns app.main.core
  (:require [app.main.options :as options]))

(set! *warn-on-infer* true)

(def electron       (js/require "electron"))
(def app            (.-app electron))
(def browser-window (.-BrowserWindow electron))

(def main-window (atom nil))

(defn create-window []
  (reset! main-window (browser-window. (clj->js (merge options/launcher
                                                       options/default))))

  (.loadURL @main-window (str "file://" js/__dirname "/public/index.html#/launcher"))
  (.on @main-window "ready-to-show" (fn []
                                      (.show @main-window)
                                      (.focus @main-window)))
  (.on @main-window "closed" #(reset! main-window nil)))

(defn main []
  (.on app "window-all-closed" #(when-not (= js/process.platform "darwin")
                                  (.quit app)))
  (.on app "activate" #(.show @main-window))
  (.on app "ready" (fn [] (create-window))))
