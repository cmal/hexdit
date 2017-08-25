(ns renderer.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [devtools.core :as devtools]
            [renderer.db]
            [renderer.component.sidebar :refer [sidebar]]
            [renderer.component.mdlist :refer [mdlist]]))

(set! *warn-on-infer* true)
(devtools/install!)
(enable-console-print!)

(defn app []
  [:div {:class "main"}
    [sidebar]
    [:div {:class "content"}
      [mdlist]]])

(defn ^export main []
  (rf/dispatch-sync [:initialize])
  (reagent/render
    [app]
    (js/document.getElementById "app-container")))
