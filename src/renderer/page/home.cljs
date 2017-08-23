(ns renderer.page
  (:require [reagent.core :as reagent]
            [renderer.component :refer [navbar]]))

(defn home []
  [:div {:class "page home"}
   [navbar]])
