(ns app.renderer.events
  (:require [re-frame.core :as rf]
            [app.renderer.db :as db]
            [app.renderer.ipc :as ipc]
            [app.renderer.constants :as constants]))

(def app-db db/default-db)

(rf/reg-event-db
  :initialize
  (fn [_ _]
    (let [bloggers (ipc/get-bloggers)
          blog-view (:id (first constants/menu))]
      (merge app-db {:bloggers bloggers
                     :blog-view blog-view}))))

(rf/reg-event-db
  :current-page
  (fn [db [_ page]]
    (merge db {:current-page page})))

(rf/reg-event-db
  :current-blog
  (fn [db [_ blog]]
    (do
      (if (nil? blog)
        (ipc/close-blog)
        (ipc/open-blog))
      (merge db {:current-blog blog}))))

(rf/reg-event-db
  :add-blog
  (fn [db [_ blog]]
    (let [bloggers (ipc/add-blog blog)]
      (merge db {:bloggers bloggers}))))

(rf/reg-event-db
  :update-blog
  (fn [db [_ blog]]
    (let [bloggers (ipc/update-blog blog)]
      (merge db {:bloggers bloggers}))))

(rf/reg-event-db
  :delete-blog
  (fn [db [_ uuid]]
    (let [bloggers (ipc/delete-blog uuid)]
      (merge db {:bloggers bloggers}))))

(rf/reg-event-db
  :blog-view
  (fn [db [_ view]]
    (merge db {:blog-view view})))
