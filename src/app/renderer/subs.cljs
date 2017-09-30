(ns app.renderer.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :bloggers
  (fn [db]
    (:bloggers db)))

(rf/reg-sub
  :current-page
  (fn [db]
    (:current-page db)))
