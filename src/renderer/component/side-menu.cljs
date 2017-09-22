(ns renderer.component.side-menu
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [renderer.common :refer [menu-info]]))

(def default-page @(rf/subscribe [:current-page]))

(def style-menu {:background-color "#171f26"
                 :height "100vh"})

(def style-menu-item {:font-size "14px"
                      :padding-left "30px"})

(defn side-menu-component []
  [ant/menu {:theme "dark"
             :style style-menu}
    (for [item menu-info]
      ^{:key (:id item)}
      [ant/menu-item {:style style-menu-item}
        (reagent/as-element
          [:span
            [ant/icon {:type (:icon item)}]
            (:label item)])])])

