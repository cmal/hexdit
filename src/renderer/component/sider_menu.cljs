(ns renderer.component.sider-menu
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [renderer.common :refer [menu-info]]))

(def default-page @(rf/subscribe [:current-page]))

(defn sider-menu-component []
  [ant/menu {:theme "dark"
             :class "sider-menu"
             :defaultSelectedKeys (clj->js [(:id (first menu-info))])}
    (for [item menu-info]
      ^{:key (:id item)}
      [ant/menu-item {:class "sider-menu-item"
                      :key (:id item)}
        (reagent/as-element
          [:span
            [ant/icon {:type (:icon item)}]
            (:label item)])])])

