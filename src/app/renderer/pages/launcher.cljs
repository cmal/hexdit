(ns app.renderer.pages.launcher
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.components.icon :refer [icon]]))

(defstylesheet styles
  [.warpper {:color "#fafafa"
              :width "100%"
              :height "100vh"}]
  [.header {:height "70px"
            :padding "25px 10px 10px"
            :display "flex"
            :align-items "baseline"
            :justify-content "space-between"
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
          :font-size "34px"
          :cursor "pointer"}])

(defn launcher []
  [:div {:class warpper}
    [:header {:class header}
      [:h1 {:class title} "博客列表"]
      [icon {:type "plus"
             :class plus
             :on-click #(println "plug")}]]])
