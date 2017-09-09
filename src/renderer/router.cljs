(ns renderer.router
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [reagent.core :as reagent]
              [renderer.view.blog :refer [blog]]
              [renderer.view.index :refer [index]]
              [renderer.view.create :refer [create]]))

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
  (defroute "/" []
    (swap! app-state assoc :page :index))
  (defroute "/create" []
    (swap! app-state assoc :page :create))
  (defroute "/blog" []
    (swap! app-state assoc :page :blog))
  (hook-browser-navigation!))

(defmulti current-page #(@app-state :page))
(defmethod current-page :index []
  [index])
(defmethod current-page :create []
  [create])
(defmethod current-page :blog []
  [blog])

