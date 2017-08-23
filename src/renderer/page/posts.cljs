(ns renderer.page.posts
  (:require [reagent.core :as reagent]
            [renderer.component.sidebar :refer [sidebar]]))

(defn posts []
  [:div {:class "page posts"}
   [sidebar]])
