(ns renderer.core
  (:require [reagent.core :as reagent :refer [atom]]))

(set! *warn-on-infer* true)

(enable-console-print!)

(defn root-component []
  [:div "Hello"])

(reagent/render
  [root-component]
  (js/document.getElementById "app-container"))
