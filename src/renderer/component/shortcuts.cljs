(ns renderer.component.shortcuts
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]))

(defn shortcuts-component []
  [:div {:class "shortcuts"}
    [ant/icon {:type "retweet"
               :class "icon"}]
    [ant/icon {:type "folder"
               :class "icon"}]
    [ant/icon {:type "link"
               :class "icon"}]
    [ant/icon {:type "upload"
               :class "icon"}]])
