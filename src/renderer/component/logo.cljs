(ns renderer.component.logo
  (:require [reagent.core :as reagent]))

(defn logo-component []
  [:div {:class "logo-warpper"}
    [:img {:src "img/logo.svg"
           :class "logo"}]])

