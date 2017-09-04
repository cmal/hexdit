(ns renderer.router
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [reagent.core :as reagent]
              [renderer.view.blog :refer [blog]]
              [renderer.view.start :refer [start]]))

(def app-state (reagent/atom {}))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn initial-routes []
  (secretary/set-config! :prefix "#")
  (defroute "/blog" []
    (swap! app-state assoc :page :blog))
  (defroute "/start" []
    (swap! app-state assoc :page :start))
  (hook-browser-navigation!))

(defmulti current-page #(@app-state :page))
(defmethod current-page :blog []
  [blog])
(defmethod current-page :start []
  [start])

