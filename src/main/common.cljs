(ns main.common)

(def app-window-options
  (clj->js {:width 960
            :height 680}))

(def start-window-options
  (clj->js {:width 400
            :height 550
            :resizable false
            :show false}))
