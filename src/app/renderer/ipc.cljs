(ns app.renderer.ipc
  (:require [app.renderer.electorn :refer [ipc-send-sync ipc-send]]))

(defn get-bloggers []
  (js->clj (ipc-send-sync :get-bloggers)))

(defn open-blog []
  (ipc-send :open-blog))

(defn close-blog []
  (ipc-send :close-blog))

(defn delete-blog [uuid]
  (js->clj (ipc-send-sync :delete-blog uuid)))
