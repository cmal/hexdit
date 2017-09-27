(ns renderer.component.sider
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [renderer.component.logo :refer [logo-component]]
            [renderer.component.menu :refer [menu-component]]
            [renderer.component.shortcuts :refer [shortcuts-component]]))

(defn sider-component []
  [:div {:class "sider"}
    [logo-component]
    [menu-component]
    [shortcuts-component]])
