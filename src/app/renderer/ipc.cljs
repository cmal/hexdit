(ns app.renderer.ipc
  (:require [app.renderer.electorn :refer [ipc-renderer]]))

(defn get-bloggers []
  (js->clj (.sendSync ipc-renderer "get-bloggers")))

(defn delete-blog [uuid]
  (js->clj (.sendSync ipc-renderer "delete-blog" uuid)))
