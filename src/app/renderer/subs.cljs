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

(rf/reg-sub
  :current-blog
  (fn [db]
    (:current-blog db)))

(rf/reg-sub
  :blog-view
  (fn [db]
    (:blog-view db)))

(rf/reg-sub
  :blog-posts
  (fn [db]
    (:blog-posts db)))

(rf/reg-sub
  :blog-pages
  (fn [db]
    (:blog-pages db)))
