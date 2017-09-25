(ns renderer.component
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [renderer.common :refer [menu-info]]))

(def default-page @(rf/subscribe [:current-page]))

(defn sider-logo []
  [:div {:class "logo-warpper"}
    [:img {:src "img/logo.svg"
           :class "logo"}]])

(defn sider-menu []
  [ant/menu {:theme "dark"
             :mode "inline"
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

(defn sider-shortcuts []
  [:div {:class "shortcuts"}
    [ant/icon {:type "retweet"
               :class "icon"}]
    [ant/icon {:type "folder"
               :class "icon"}]
    [ant/icon {:type "link"
               :class "icon"}]
    [ant/icon {:type "upload"
               :class "icon"}]])

(defn sider []
  [ant/layout-sider {:width "150"}
    [:div {:class "sider"}
      [sider-logo]
      [sider-menu]
      [sider-shortcuts]]])

