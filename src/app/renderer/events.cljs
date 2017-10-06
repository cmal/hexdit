(ns app.renderer.events
  (:require [re-frame.core :as rf]
            [app.renderer.db :as db]
            [app.renderer.ipc :as ipc]))

(def app-db db/default-db)

(rf/reg-event-db
  :initialize
  (fn [_ _]
    ;  TODO: add get bloggers ipc function <2017-09-30, Ahonn Jiang> ;
    (let [bloggers (ipc/get-bloggers)]
      (merge app-db {:bloggers bloggers}))))

(rf/reg-event-db
  :current-page
  (fn [db [_ page]]
    (merge db {:current-page page})))

(rf/reg-event-db
  :current-blog
  (fn [db [_ blog]]
    (merge db {:current-blog blog})))

(rf/reg-event-db
  :delete-blog
  (fn [db [_ uuid]]
    (merge db {:bloggers (ipc/delete-blog uuid)})))
