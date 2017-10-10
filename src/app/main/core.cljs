(ns app.main.core
  (:require [app.main.ipc :as ipc]
            [app.main.options :as options]
            [app.main.electorn :refer [app browser-window]]))

(set! *warn-on-infer* true)

(def main-window (atom nil))
(def window-option (clj->js (merge options/launcher
                                   options/default)))

(defn create-window []
  (reset! main-window (browser-window. window-option))
  (.loadURL @main-window (str "file://" js/__dirname "/public/index.html#/launcher"))

  (.on @main-window "ready-to-show" #(do
                                       (.show @main-window)
                                       (.focus @main-window)))
  (.on @main-window "closed" #(reset! main-window nil)))

(defn main []
  (.on app "window-all-closed" #(when-not (= (.-platform js/process) "darwin")
                                  (.quit app)))
  (.on app "activate" #(if (nil? @main-window)
                         (create-window)
                         (.show @main-window)))
  (.on app "ready" #(create-window)))
