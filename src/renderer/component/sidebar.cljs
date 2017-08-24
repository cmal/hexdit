(ns renderer.component.sidebar
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [class-names.core :refer [class-names]]
            [renderer.constant :refer [menu-list]]))

(defn click-menu-item [item]
 (rf/dispatch-sync [:switch-page (:id item)]))

(defn menu-item [item]
  (let [item-id (:id item)
        current-id @(rf/subscribe [:current-page])
        is-active? (= item-id current-id)]
    [:li {:class (class-names "menu-item" {:active is-active?})
          :on-click #(click-menu-item item)}
     [:img {:class "menu-item-icon"
            :src (:icon item)}]
     [:span (:label item)]]))

(defn menu [menu-list]
  [:div {:class "menu-warp"}
      [:ul {:class "menu"}
       (for [item menu-list]
         ^{:key (:id item)}
         [menu-item item])]])

(defn sidebar []
  [:div {:class "sidebar"}
   [:div {:class "logo-warp"}
    [:img {:class "logo"
           :src "img/logo.svg"}]]
   [menu menu-list]])

