(ns renderer.view.create
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [renderer.component.create-blog-form :refer [create-blog-form-component]]))

(defn create []
  [ant/layout {:class "create layout"}
    [ant/layout-header {:class "create-header"}
     "添加博客"]
    [ant/layout-content
      [create-blog-form-component]]])
