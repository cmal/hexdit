(ns app.renderer.pages.launcher
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [re-frame.core :as rf]
            [secretary.core :as secretary]
            [app.renderer.components.icon :refer [icon]]
            [app.renderer.components.header :refer [header]]
            [app.renderer.components.bloggers :refer [bloggers]]))

(defstylesheet styles
  [.warpper {:background "#fafafa"
             :width "100%"
             :height "100vh"}]
  [.plus {:position "relative"
          :top "5px"
          :margin-right "5px"
          :font-size "34px"}]
  [.plus::before {:cursor "pointer"}])

(defn create-blog [evt]
  (.stopPropagation evt)
  (secretary/dispatch! "/create"))

(defn launcher []
  [:div {:class warpper}
   [header {:title "博客列表"}
    [icon {:type "plus"
           :class plus
           :on-click #(create-blog %)}]]
   [bloggers]])
