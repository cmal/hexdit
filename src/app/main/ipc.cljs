(ns app.main.ipc
  (:require [app.main.config :as config]
            [app.main.electorn :refer [ipc-main]]))

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

