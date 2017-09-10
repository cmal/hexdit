(ns renderer.view.index
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [secretary.core :as secretary]
            [renderer.component.blog-list :refer [blog-list-component]]))

(defn click-create-blog []
  (secretary/dispatch! "/create"))

(defn index []
  [ant/layout {:class "index layout"}
    [ant/layout-header {:class "index-header"}
      "博客列表"]
    [ant/layout-content {:class "index-content"}
      [blog-list-component]]
    [ant/layout-footer {:class "index-footer"}
      [ant/button {:type "primary"
                   :class "create-blog"
                   :on-click #(click-create-blog)}
        [ant/icon {:type "plus"
                   :class "icon"}
          "新建博客"]]]])
