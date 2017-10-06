(ns app.renderer.pages.launcher
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [re-frame.core :as rf]
            [app.renderer.components.icon :refer [icon]]
            [app.renderer.components.bloggers :refer [bloggers]]))

(defstylesheet styles
  [.warpper {:background "#fafafa"
             :width "100%"
             :height "100vh"}]
  [.header {:height "70px"
            :width "100%"
            :padding "25px 12px 10px"
            :display "flex"
            :align-items "baseline"
            :justify-content "space-between"
            :position "fixed"
            :color "#fafafa"
            :background "#2f3235"
            :box-sizing "border-box"
            :-webkit-app-region "drag"}]
  [.title {:margin "0"
           :display "inline-block"
           :font-size "26px"
           :font-weight "normal"}]
  [.plus {:position "relative"
          :top "5px"
          :margin-right "5px"
          :font-size "34px"}]
  [.plus::before {:cursor "pointer"}])

(defn launcher []
  [:div {:class warpper}
   [:header {:class header}
    [:h1 {:class title} "博客列表"]
    [icon {:type "plus"
           :class plus
           :on-click #(println @(rf/subscribe [:bloggers]))}]]
   [bloggers]])
