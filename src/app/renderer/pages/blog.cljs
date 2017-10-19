(ns app.renderer.pages.blog
  (:require [reagent.core :as reagent]
            [re-frame.core :as fs]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.components.sider :refer [sider]]))

(defn blog []
  [:div
   [sider]])
