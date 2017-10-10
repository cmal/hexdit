(ns app.renderer.pages.edit
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [re-frame.core :as rf]
            [app.renderer.components.header :refer [header]]
            [app.renderer.components.form :refer [form]]))

(defstylesheet styles
  [.warpper {:background "#fafafa"
             :width "100%"
             :height "100vh"}])

(defn edit [blog]
  [:div {:class warpper}
   [header {:title "修改博客"}]
   [form blog]])

