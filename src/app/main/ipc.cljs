(ns app.main.ipc
  (:require [app.main.config :as config]
            [app.main.options :as options]
            [app.main.electorn :refer [reg-ipc-event
                                       set-window-option
                                       hide-window]]
            [app.main.utils :refer [index-of make-uuid]]
            [app.main.hexo :as hexo]))

(reg-ipc-event
  :get-bloggers
  (fn [evt _]
    (let [value (config/get-field "bloggers")
          bloggers (or value (array))]
      (if (not value)
        (config/set-field "bloggers" (array)))
      (aset evt "returnValue" bloggers))))

(reg-ipc-event
  :add-blog
  (fn [evt blog]
    (let [bloggers (js->clj (config/get-field "bloggers"))
          new-uuid (make-uuid)
          new-blog (merge (js->clj blog) {"uuid" new-uuid
                                          "date" (.now js/Date)})
          new-bloggers (clj->js (conj bloggers new-blog))]
      (if (hexo/check-pkg new-blog)
        (do
          (config/set-field "bloggers" new-bloggers)
          (aset evt "returnValue" new-bloggers))
        (aset evt "returnValue" (clj->js bloggers))))))

(reg-ipc-event
  :update-blog
  (fn [evt blog]
    (let [bloggers (js->clj (config/get-field "bloggers"))
          uuid (aget blog "uuid")
          update-index (index-of #(= (get % "uuid") uuid) bloggers)
          new-bloggers (clj->js (concat (subvec bloggers 0 update-index)
                                        [(js->clj blog)]
                                        (subvec bloggers (inc update-index))))]
      (config/set-field "bloggers" new-bloggers)
      (aset evt "returnValue" new-bloggers))))

(reg-ipc-event
  :delete-blog
  (fn [evt uuid]
    (let [bloggers (js->clj (config/get-field "bloggers"))
          delete-index (index-of #(= (get % "uuid") uuid) bloggers)
          new-bloggers (clj->js (concat (subvec bloggers 0 delete-index)
                                        (subvec bloggers (inc delete-index))))]
      (config/set-field "bloggers" new-bloggers)
      (aset evt "returnValue" new-bloggers))))

(reg-ipc-event
  :open-blog
  (fn [evt blog]
    (hide-window)
    (hexo/load blog)
    (js/setTimeout
      (fn []
        (set-window-option options/main))
      500)))

(reg-ipc-event
  :close-blog
  (fn [evt]
    (set-window-option options/launcher)))

(reg-ipc-event
  :get-blog-posts
  (fn [evt]
    (let [posts (hexo/get-posts)]
      (aset evt "returnValue" (clj->js posts)))))

