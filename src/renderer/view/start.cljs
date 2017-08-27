(ns renderer.view.start
  (:require [reagent.core :as reagent]
            [renderer.component.blog-list :refer [blog-list-component]]))

(defn start []
  [:div {:class "start view"}
   [blog-list-component]])
