(ns app.renderer.components.markdown
  [:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.components.search :refer [search]]])

(defstylesheet styles
  [.warpper {:width "260px"
             :height "100vh"
             :position "fixed"
             :margin-left "180px"
             :background "#fafafa"
             :border-right "1px solid #dedede"}])

(defn markdown []
  [:div {:class warpper}
   [search]])

