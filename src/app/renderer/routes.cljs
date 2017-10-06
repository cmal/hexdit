(ns app.renderer.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [secretary.core :as secretary]
            [reagent.core :as reagent]
            [re-frame.core :as rf]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [app.renderer.pages.launcher :refer [launcher]]
            [app.renderer.pages.blog :refer [blog]]))

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
    (rf/dispatch-sync [:current-page :launcher]))
  (defroute "/blog" []
    (rf/dispatch-sync [:current-page :blog])))

(defmulti current-page
  (fn []
    @(rf/subscribe [:current-page])))
(defmethod current-page :launcher []
  [launcher])
(defmethod current-page :blog []
  [blog])
(defmethod current-page :default [])

(defn initialize []
  (secretary/set-config! :prefix "#")
  (dispatch-routes)
  (hook-browser-navigation!))

