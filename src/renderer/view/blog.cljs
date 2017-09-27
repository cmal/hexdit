(ns renderer.view.blog
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [renderer.component.sider :refer [sider-component]]
            [renderer.component.content :refer [content-component]]))

(defn blog []
  (let [blog-info (clj->js @(rf/subscribe [:current-blog]))]
    [ant/layout {:class "blog layout"}
      [ant/layout-sider {:width "150"}
        [sider-component]]
      [ant/layout-content
        [content-component]]]))



