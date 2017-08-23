(ns renderer.router
    (:require-macros [secretary.core :refer [defroute]])
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [renderer.page.pages :refer [pages]]
              [renderer.page.posts :refer [posts]])
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
  (secretary/set-config! :prefix "#")
  (defroute "/" []
    (swap! app-state assoc :page :posts))
  (defroute "/pages" []
    (swap! app-state assoc :page :pages))
  (hook-brower-navigation!))

(defmulti current-page #(@app-state :page))
(defmethod current-page :posts [] 
  [posts])
(defmethod current-page :pages [] 
  [pages])
