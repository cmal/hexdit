(ns renderer.component.menu
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [renderer.common :refer [menu-info]]))

(def default-page @(rf/subscribe [:current-page]))

(defn click-menu-item [item]
  (let [ikey (aget item "key")]
    (rf/dispatch-sync [:switch-page ikey])))

(defn menu-component []
  [ant/menu {:theme "dark"
             :mode "inline"
             :class "sider-menu"
             :defaultSelectedKeys (clj->js [(:id (first menu-info))])
             :onClick #(click-menu-item %)}
    (for [item menu-info]
      ^{:key (:id item)}
      [ant/menu-item {:class "sider-menu-item"
                      :key (:id item)}
        (reagent/as-element
          [:span
            [ant/icon {:type (:icon item)}]
            (:label item)])])])

