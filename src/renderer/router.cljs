(ns renderer.router
    (:require-macros [secretary.core :refer [defroute]])
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [renderer.page :refer [home]])
    (:import goog.History))

(def app-state (atom {}))

(defn hook-brower-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routers []
  (defroute "/" []
    (swap! app-state assoc :page :home))
  (defroute "/post" []
    (swap! app-state assoc :page :home))
  (hook-brower-navigation!))

(defmulti current-page #(@app-state :page))
(defmethod current-page :home [] 
  [home])
