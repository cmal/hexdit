(ns renderer.db
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [renderer.ipc :as ipc]
            [renderer.common :refer [menu-list]]))

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {:blog-list (ipc/get-blog-list)
     :current-blog nil
     :current-page (:id (first menu-list))}))

;; ======== event ========
(rf/reg-event-db
  :switch-blog
  (fn [db [_ blog]]
    (assoc db :current-blog blog)))

(rf/reg-event-db
  :switch-page
  (fn [db [_ page]]
    (assoc db :current-page page)))

(rf/reg-event-db
  :remove-blog
  (fn [db [_ idx]]
    (assoc db :blog-list (ipc/remove-blog idx))))

(rf/reg-event-db
  :add-blog
  (fn [db [_ blog]]
    (assoc db :blog-list (ipc/add-blog blog))))

;; ======== subscribe ========
(rf/reg-sub
  :blog-list
  (fn [db _]
    (:blog-list db)))

(rf/reg-sub
  :current-blog
  (fn [db _]
    (:current-blog db)))

(rf/reg-sub
  :current-page
  (fn [db _]
    (:current-page db)))

