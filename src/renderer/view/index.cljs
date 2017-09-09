(ns renderer.view.index
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [renderer.component.blog-list :refer [blog-list-component]]
            [renderer.component.create-blog :refer [create-blog-component]]))

(defn index []
  [ant/layout {:class "index layout"}
    [ant/layout-header {:class "list-header"}
      "博客列表"]
    [ant/layout-content
      [blog-list-component]]
    [ant/layout-footer {:class "list-footer"}
      [create-blog-component]]])
