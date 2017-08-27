(ns renderer.view.blog
  (:require [reagent.core :as reagent]
            [renderer.component.sidebar :refer [sidebar-component]]
            [renderer.component.article-list :refer [article-list-component]]))

(defn blog []
  [:div {:class "blog view"}
    [sidebar-component]
    [:div {:class "content"}
      [article-list-component]]])
