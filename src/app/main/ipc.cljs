(ns app.main.ipc
  (:require [app.main.config :as config]
            [app.main.options :as options]
            [app.main.electorn :refer [reg-ipc-event set-window-option]]
            [app.main.utils :refer [index-of]]))

(reg-ipc-event
  :get-bloggers
  (fn [evt _]
    (let [value (config/get-field "bloggers")
          bloggers (or value (array))]
      (if (not value)
        (config/set-field "bloggers" (array)))
      (aset evt "returnValue" bloggers))))

(reg-ipc-event
  :open-blog
  (fn [evt]
    (set-window-option options/main)))

(reg-ipc-event
  :close-blog
  (fn [evt]
    (set-window-option options/launcher)))

(reg-ipc-event
  :delete-blog
  (fn [evt uuid]
    (let [bloggers (js->clj (config/get-field "bloggers"))
          delete-index (index-of #(= (get % "uuid") uuid) bloggers)
          new-bloggers (clj->js (concat (subvec bloggers 0 delete-index)
                                        (subvec bloggers (inc delete-index))))]
      (config/set-field "bloggers" new-bloggers)
      (aset evt "returnValue" new-bloggers))))
