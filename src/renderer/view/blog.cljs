(ns renderer.view.blog
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [renderer.component.side-menu :refer [side-menu-component]]))

(defn blog []
  (let [blog-info (clj->js @(rf/subscribe [:current-blog]))]
    [ant/layout {:class "blog layout"
                 :data-info (.stringify js/JSON blog-info)}
      [ant/layout-sider
        [side-menu-component]]]))


