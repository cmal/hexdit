(ns app.renderer.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [secretary.core :as secretary]
            [re-frame.core :as rf]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [app.renderer.pages.launcher :as launcher]))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn dispatch-routes []
  (defroute "/launcher" []
    (rf/dispatch-sync [:current-page :launcher])))

(defmulti pages identity)
(defmethod pages :launcher []
  [launcher/main])
(defmethod pages :default [])

(defn start! []
  (secretary/set-config! :prefix "#")
  (dispatch-routes)
  (hook-browser-navigation!))

