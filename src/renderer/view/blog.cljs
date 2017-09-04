(ns renderer.view.blog
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [renderer.component.sidebar :refer [sidebar-component]]
            [renderer.component.article-list :refer [article-list-component]]))

(defn blog []
  (let [blog-info @(rf/subscribe [:current-blog])]
    [:div {:class "blog view"}
      [sidebar-component]
      [:div {:class "content"}
        [article-list-component]]]))
