(ns app.renderer.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [app.renderer.events]
            [app.renderer.subs]
            [app.renderer.routes :as routes :refer [pages]]))

(enable-console-print!)

(defn start []
  (reagent/render
    [:div {:class "root"}
      [pages @(rf/subscribe [:current-page])]]
    (js/document.getElementById "app-container")))

(defn init []
  (rf/dispatch-sync [:initialize])
  (routes/start!)
  (start))

