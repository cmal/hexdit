(ns renderer.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [devtools.core :as devtools]
            [renderer.db]
            [renderer.router :as router]))

(set! *warn-on-infer* true)
(devtools/install!)
(enable-console-print!)

(defn ^export main []
  (router/initial-routes)
  (rf/dispatch-sync [:initialize])
  (reagent/render
    [router/current-page]
    (js/document.getElementById "app-container")))
