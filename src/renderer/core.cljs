(ns renderer.core
  (:require [reagent.core :as reagent :refer [atom]]
            [devtools.core :as devtools]
            [renderer.component.sidebar :refer [sidebar]]
            [renderer.router :refer [app-routers current-page]]))

(set! *warn-on-infer* true)
(devtools/install!)
(enable-console-print!)

(defn app []
  [:div {:class "main"}
   [sidebar]
   [current-page]])

(defn ^export main []
  (app-routers)
  (reagent/render
    [app]
    (js/document.getElementById "app-container")))
