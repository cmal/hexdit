(ns renderer.db
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [renderer.constant :refer [menu-list]]))

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {:current-page (:id (first menu-list))}))

(rf/reg-event-db
  :switch-page
  (fn [db [_ page]]
    (assoc db :current-page page)))

(rf/reg-sub
  :current-page
  (fn [db _]
    (:current-page db)))
