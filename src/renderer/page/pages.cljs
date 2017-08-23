(ns renderer.page.pages
  (:require [reagent.core :as reagent]
            [renderer.component.sidebar :refer [sidebar]]))

(defn pages []
  [:div {:class "page pages"}
   [sidebar]])
