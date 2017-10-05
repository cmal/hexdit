(ns app.renderer.ipc
  (:require [app.renderer.electorn :refer [ipc-send-sync]]))

(defn get-bloggers []
  (js->clj (ipc-send-sync :get-bloggers)))

(defn delete-blog [uuid]
  (js->clj (ipc-send-sync :delete-blog uuid)))
