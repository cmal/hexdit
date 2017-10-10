(ns app.renderer.components.form
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]))

(defstylesheet styles
  [.wrapper {:padding-top "70px"}]
  [.form-item {:margin-bottom "20px"}])

(def state (reagent/atom {:title nil}))

(defn will-mount [this value]
  (js/console.log this value))

(defn- render []
  [:form {:class wrapper}
   [:div {:class form-item}
    [:input {:type "text"}]]])

(defn form [{:keys [value]}]
  (let [value (clj->js (or value {}))]
    (reagent/create-class
      {:component-will-mount #(will-mount % value)
       :reagent-render #(render)})))
