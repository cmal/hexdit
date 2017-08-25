(ns renderer.view.app
  (:require [reagent.core :as reagent]
            [renderer.component.sidebar :refer [sidebar]]
            [renderer.component.mdlist :refer [mdlist]]))

(defn app []
  [:div {:class "app view"}
    [sidebar]
    [:div {:class "content"}
      [mdlist]]])
