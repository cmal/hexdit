(ns renderer.db
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [renderer.common :refer [menu-list]]))

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {:current-blog nil
     :current-page (:id (first menu-list))}))

;; event
(rf/reg-event-db
  :switch-blog
  (fn [db [_ blog]]
    (assoc db :current-blog blog)))

(rf/reg-event-db
  :switch-page
  (fn [db [_ page]]
    (assoc db :current-page page)))

;; subscribe
(rf/reg-sub
  :current-blog
  (fn [db _]
    (:current-blog db)))

(rf/reg-sub
  :current-page
  (fn [db _]
    (:current-page db)))

