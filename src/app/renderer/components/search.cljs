(ns app.renderer.components.search
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.components.icon :refer [icon]]))

(defstylesheet style
  [.warpper {:width "259px"
             :height "50px"
             :position "fixed"
             :background "#f9f9f9"
             :border-bottom "1px solid #dedede"
             :-webkit-app-region "drag"}]
  [.search-wrapper {:width "100%"
                    :height "100%"
                    :padding-right "40px"}]
  [.search-input {:width "calc(100% - 20px)"
                  :height "26px"
                  :padding-left "25px"
                  :margin "12px 5px 10px 15px"
                  :border "1px solid #cacaca"
                  :border-radius "4px"}]
  [.search-icon {:position "absolute"
                 :font-size "16px"
                 :top "17px"
                 :left "23px"
                 :color "#a0a0a0"}]
  [.edit-button {:position "absolute"
                 :top "0"
                 :right "0"
                 :padding 0
                 :width "40px"
                 :background "#f9f9f9"
                 :border "none"}]
  [.edit-icon {:font-size "22px"
               :padding-top "13px"
               :color "#a0a0a0"}])


(defn search []
  [:div {:class warpper}
   [:div {:class search-wrapper}
    [:input {:class search-input
             :placeholder "搜索"}]
    [icon {:type "search"
           :class search-icon}]]
   [:button {:class edit-button}
      [icon {:type "edit"
             :class edit-icon
             :on-click #(print "click")}]]])
