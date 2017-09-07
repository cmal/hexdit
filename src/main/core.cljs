(ns main.core
  (:require [main.ipc]
            [main.config :refer [get-config set-config]]
            [main.common :refer [app-window-options start-window-options]]))

(set! *warn-on-infer* true)

(def electron       (js/require "electron"))
(def app            (.-app electron))
(def browser-window (.-BrowserWindow electron))

(def main-window (atom nil))

(defn create-window []
  (reset! main-window (browser-window. start-window-options))

  (.loadURL @main-window (str "file://" js/__dirname "/public/index.html#/start"))
  (.on @main-window "ready-to-show" (fn []
                                      (.show @main-window)
                                      (.focus @main-window)))
  (.on @main-window "closed" #(reset! main-window nil)))

(.on app "window-all-closed" #(when-not (= js/process.platform "darwin")
                                (.quit app)))
(.on app "activate" #(.show @main-window))
(.on app "ready" (fn []
                   (create-window)))
