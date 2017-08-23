(ns renderer.component.sidebar
  (:require [reagent.core :as reagent :refer [atom]]
            [class-names.core :refer [class-names]]))

(def menu-list [{:id "posts" :label "文章" :link "#/"}
                {:id "pages" :label "页面" :link "#/pages"}])

(def selected-id (atom (:id (first menu-list))))

(defn click-menu-item [item]
  (let [current-id (:id item)]
    (reset! selected-id current-id)))

(defn menu-item [item]
  [:li {:class (class-names "menu-item"
                            {:active (= (:id item) @selected-id)})}
    [:a {:class "menu-item-link" 
         :href (:link item)
         :on-click #(click-menu-item item)}
     (:label item)]])

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

