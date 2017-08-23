(ns renderer.component.sidebar
  (:require [reagent.core :as reagent]))

(def menu-list [{:label "文章" :link "#/"}
                {:label "页面" :link "#/pages"}])

(defn menu-item [item]
  (let [link (:link item)
        label (:label item)]
    [:li {:class "menu-item"}
            [:a {:class "menu-item-link" :href link} label]]))

(defn menu [menu-list]
  [:div {:class "menu-warp"}
      [:ul {:class "menu"}
       (for [item menu-list]
         ^{:key (:link item)}
         [menu-item item])]])

(defn sidebar []
  [:div {:class "sidebar"}
   [:div {:class "logo-warp"}
    [:img {:class "logo" :src "img/logo.svg"}]]
   [menu menu-list]])

