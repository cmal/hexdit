(ns renderer.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [devtools.core :as devtools]
            [renderer.db]
            [renderer.router :refer [initial-routes current-page]]))

(set! *warn-on-infer* true)
(if ^boolean goog/DEBUG (devtools/install!))
(enable-console-print!)

(defn ^export main []
  (initial-routes)
  (rf/dispatch-sync [:initialize])
  (reagent/render
    [current-page]
    (js/document.getElementById "app-container")))
