(ns renderer.component.content
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [renderer.component.md-list :refer [md-list-component]]))

(defn content-component []
  [ant/layout {:class "layout"}
    [md-list-component]])
