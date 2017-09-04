(ns renderer.view.start
  (:require [reagent.core :as reagent]
            [secretary.core :as secretary]
            [renderer.component.blog-list :refer [blog-list-component]]))

(defn click-create-blog []
  (secretary/dispatch! "/blog"))

(defn start []
  [:div {:class "start view"}
   [:div {:class "start-header"}
    [:h1 {:class "start-title"}
      "博客列表"]]
   [:div {:class "start-body"}
    [blog-list-component]
    [:button {:class "create-blog"
              :on-click #(click-create-blog)}
      [:i {:class "fa fa-plus"}]]]])
