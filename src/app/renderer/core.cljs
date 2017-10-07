(ns app.renderer.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [devtools.core :as devtools]
            [app.renderer.events]
            [app.renderer.subs]
            [app.renderer.routes :as routes :refer [current-page]]))

(devtools/install!)
(enable-console-print!)

(defn start []
  (reagent/render
    [:div {:class "root"}
      [current-page]]
    (js/document.getElementById "app-container")))

(defn init []
  (rf/dispatch-sync [:initialize])
  (routes/initialize)
  (start))

