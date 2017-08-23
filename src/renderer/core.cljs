(ns renderer.core
  (:require [reagent.core :as reagent :refer [atom]]
            [renderer.router :as router :refer [app-routers current-page]]))

(set! *warn-on-infer* true)
(enable-console-print!)

(app-routers)

(reagent/render
  [current-page]
  (js/document.getElementById "app-container"))
