(ns main.core)

(set! *warn-on-infer* true)

(def electron       (js/require "electron"))
(def app            (.-app electron))
(def browser-window (.-BrowserWindow electron))

(def main-window (atom nil))

(defn create-window []
  (reset! main-window (browser-window.
                        (clj->js {:width 960
                                  :height 680})))

  (.loadURL @main-window (str "file://" js/__dirname "/public/index.html"))
  (.on @main-window "closed" #(reset! main-window nil)))

(.on app "window-all-closed" #(when-not (= js/process.platform "darwin")
                                (.quit app)))
(.on app "activate" (fn [] 
                      (.show @main-window)))
(.on app "ready" create-window)
