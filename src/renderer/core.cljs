(ns renderer.core
  (:require [reagent.core :as reagent :refer [atom]]
            [devtools.core :as devtools]
            [renderer.router :as router :refer [app-routers current-page]]))

(set! *warn-on-infer* true)
(devtools/install!)
(enable-console-print!)

(defn ^export main []
  (app-routers)
  (reagent/render
    [current-page]
    (js/document.getElementById "app-container")))
