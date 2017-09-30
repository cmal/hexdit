(ns app.renderer.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [app.renderer.events]
            [app.renderer.subs]
            [app.renderer.routes :as routes :refer [current-page]]))

(enable-console-print!)

(defn render []
  (reagent/render
    [:div {:class "root"}
      [current-page]]
    (js/document.getElementById "app-container")))

(defn start []
  (routes/start!)
  (rf/dispatch-sync [:initialize])
  (render))

(defn init []
  (start))

