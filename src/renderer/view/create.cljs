(ns renderer.view.create
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [renderer.component.blog-form :refer [blog-form-component]]))

(defn create []
  [ant/layout {:class "create layout"}
    [ant/layout-header {:class "create-header"}
     "添加博客"]
    [ant/layout-content {:class "create-content"}
      [blog-form-component]]])
