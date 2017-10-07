(ns app.renderer.pages.create
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [re-frame.core :as rf]
            [app.renderer.components.header :refer [header]]))

(defstylesheet styles
  [.warpper {:background "#fafafa"
             :width "100%"
             :height "100vh"}])

(defn create []
  [:div {:class warpper}
   [header {:title "添加博客"}]])
