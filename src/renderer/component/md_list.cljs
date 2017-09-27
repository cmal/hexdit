(ns renderer.component.md-list
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]))

(defn md-list-component []
  [ant/layout-sider {:width "180"}
    [:div {:class "md-list"}]])

