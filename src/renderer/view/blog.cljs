(ns renderer.view.blog
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [renderer.component.logo :refer [logo-component]]
            [renderer.component.menu :refer [menu-component]]
            [renderer.component.shortcuts :refer [shortcuts-component]]))

(defn blog []
  (let [blog-info (clj->js @(rf/subscribe [:current-blog]))]
    [ant/layout {:class "blog layout"
                 :data-info (.stringify js/JSON blog-info)}
      [ant/layout-sider {:width "150"}
        [:div {:class "sider"}
          [logo-component]
          [menu-component]
          [shortcuts-component]]]]))



