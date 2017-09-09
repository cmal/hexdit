(ns renderer.component.create-blog
  (:require [reagent.core :as reagent]
            [secretary.core :as secretary]
            [antizer.reagent :as ant]))

(defn click-create-blog []
  (secretary/dispatch! "/create"))

(defn create-blog-component []
  [ant/button {:type "primary"
               :class "create-blog"
               :on-click #(click-create-blog)}
    [:i {:class "fa fa-plus"}
      "新建博客"]])
