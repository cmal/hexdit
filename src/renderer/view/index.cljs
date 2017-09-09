(ns renderer.view.index
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [secretary.core :as secretary]
            [renderer.component.blog-list :refer [blog-list-component]]))

(defn click-create-blog []
  (secretary/dispatch! "/create"))

(defn index []
  [ant/layout {:class "index layout"}
    [ant/layout-header {:class "list-header"}
      "博客列表"]
    [ant/layout-content
      [blog-list-component]]
    [ant/layout-footer {:class "list-footer"}
      [ant/button {:type "primary"
                   :class "create-blog"}
        [:i {:class "fa fa-plus"}
          "新建博客"]]]])
