(ns app.renderer.components.header
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]))

(defstylesheet styles
  [.warpper {:height "70px"
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
           :font-weight "normal"}])

(defn header [props children]
  [:header {:class warpper}
   [:h1 {:class title} (:title props)]
   children])
