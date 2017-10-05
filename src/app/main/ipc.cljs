(ns app.main.ipc
  (:require [app.main.config :as config]
            [app.main.electorn :refer [ipc-main]]
            [app.main.utils :refer [index-of]]))

;; define ipc function
(defn defipc [ipcname ipcfn]
  (.on ipc-main (name ipcname) ipcfn))

(defipc :get-bloggers
  (fn [evt _]
    (let [value (config/get-field "bloggers")
          bloggers (or value (array))]
      (if (not value)
        (config/set-field "bloggers" (array)))
      (aset evt "returnValue" bloggers))))

(defipc :delete-blog
  (fn [evt uuid]
    (let [bloggers (js->clj (config/get-field "bloggers"))
          delete-index (index-of #(= (get % "uuid") uuid) bloggers)
          new-bloggers (clj->js (concat (subvec bloggers 0 delete-index)
                                        (subvec bloggers (inc delete-index))))]
      (config/set-field "bloggers" new-bloggers)
      (aset evt "returnValue" new-bloggers))))
