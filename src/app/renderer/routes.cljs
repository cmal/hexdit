(ns app.renderer.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [reagent.core :as reagent]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [app.renderer.pages.launcher :as launcher]))

(def app-state (reagent/atom {}))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn dispatch-routes []
  (defroute "/launcher" []
    (swap! app-state assoc :page :launcher)))

(defmulti current-page #(@app-state :page))
(defmethod current-page :launcher []
  [launcher/main])

(defn start! []
  (secretary/set-config! :prefix "#")
  (dispatch-routes)
  (hook-browser-navigation!))

