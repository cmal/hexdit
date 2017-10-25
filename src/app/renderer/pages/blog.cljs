(ns app.renderer.pages.blog
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.components.sider :refer [sider]]
            [app.renderer.components.markdown :refer [markdown]]))

(defstylesheet styles
  [.warpper {:width "100vw"
             :height "100vh"
             :overflow "auto"}])

(defn blog []
  [:div {:class warpper}
   [sider]
   [markdown]])
