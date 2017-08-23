(ns renderer.component
  (:require [reagent.core :as reagent]))

(set! *warn-on-infer* true)
(enable-console-print!)

(defn navbar []
  [:div {:class "navbar"}])
