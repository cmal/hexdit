(ns app.renderer.ipc
  (:require [app.renderer.electorn :refer [ipc-send-sync ipc-send]]))

(defn get-bloggers []
  (js->clj (ipc-send-sync :get-bloggers)))

(defn add-blog [blog]
  (js->clj (ipc-send-sync :add-blog blog)))

(defn update-blog [blog]
  (js->clj (ipc-send-sync :update-blog blog)))

(defn delete-blog [uuid]
  (js->clj (ipc-send-sync :delete-blog uuid)))

(defn open-blog [blog]
  (ipc-send :open-blog blog))

(defn close-blog []
  (ipc-send :close-blog))

(defn get-blog-posts []
  (js->clj (ipc-send-sync :get-blog-posts)))
