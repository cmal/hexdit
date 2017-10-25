(ns app.renderer.components.sider
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [forest.macros :refer-macros [defstylesheet]]
            [forest.class-names :refer [class-names]]
            [app.renderer.constants :as constants]
            [app.renderer.components.icon :refer [icon]]))

(defstylesheet styles
  [.wrapper {:width "180px"
             :height "100vh"
             :position "fixed"
             :left "0"
             :background "#2f3235"}]
  [.drag-region {:height "50px"
                 :-webkit-app-region "drag"}]
  [.menu-wrapper {}]
  [.item-wrapper {:display "flex"
                  :align-items "baseline"
                  :padding "3px 25px"
                  :color "#ffffff"
                  :font-size "14px"
                  :font-weight "lighter"}]
  [.item-icon {:width "15px"
               :font-size "16px"
               :color "#e0e0e0"
               :margin-right "12px"
               :text-align "center"}]
  [.active-item {:background "#dd4c4f"}])

(defn selected? [item]
  (let [blog-view @(rf/subscribe [:blog-view])]
    (= blog-view (:id item))))

(defn select-item [item]
  (let [view (:id item)]
    (rf/dispatch-sync [:blog-view view])))

(defn menu-item [item]
  [:div {:class (class-names item-wrapper
                             {active-item (selected? item)})
         :on-click #(select-item item)}
   [icon {:type (:icon item)
          :class item-icon}]
   [:span (:text item)]])

(defn menu []
  [:div {:class menu-wrapper}
   (for [item constants/menu]
     ^{:key (:id item)}
     [menu-item item])])

(defn sider []
  [:nav {:class wrapper}
   [:div {:class drag-region}]
   [menu]])
