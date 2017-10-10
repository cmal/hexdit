(ns app.renderer.components.form
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]))

(defstylesheet styles
  [.wrapper {:padding-top "70px"}]
  [.form-item {:margin-bottom "20px"}])

(def fields (reagent/atom {:title ""
                           :path ""
                           :description ""}))

(defn- swap-fields [evt ky]
  (swap! fields assoc ky (-> evt .-target .-value)))

(defn- will-mount [this value]
  (when-not (nil? value)
    (reset! fields value)))

(defn- render []
  [:form {:class wrapper}
   [:div {:class form-item}
    [:input {:type "text"
             :value (@fields :title)
             :on-change #(swap-fields % :title)}]]])

(defn form [value]
  (reagent/create-class
    {:component-will-mount #(will-mount % value)
     :reagent-render #(render)}))
