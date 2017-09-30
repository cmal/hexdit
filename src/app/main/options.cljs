(ns app.main.options)

(def launcher-window
  (clj->js {:width 400
            :height 550
            :resizable false
            :show false}))

(def main-window
  (clj->js {:width 960
            :height 680
            :resizable true}))
