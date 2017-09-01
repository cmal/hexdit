(ns renderer.view.start
  (:require [reagent.core :as reagent]
            [renderer.component.blog-list :refer [blog-list-component]]))

(defn start []
  [:div {:class "start view"}
   [:div {:class "start-header"}
    [:h1 {:class "start-title"}
      "博客列表"]]
   [:div {:class "start-body"}
    [blog-list-component]]])
