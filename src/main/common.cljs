(ns main.common)

(def app-window-options
  (clj->js {:width 960
             :height 680
             :minWidth 800
             :minHeight 550
             :show false}))

(def start-window-options
  (clj->js {:width 500
            :height 650
            :resizable false
            :show false}))
