(ns app.renderer.utils.modal
  (:require [app.renderer.electorn :refer [dialog]]))

(def show-open-dialog (.-showOpenDialog dialog))
(def show-message-box (.-showMessageBox dialog))

(defn message [{:keys [title message]}]
  (show-message-box (clj->js {:type "warning"
                              :title title
                              :message message})))

(defn confirm [{:keys [title message on-confirm on-cancel]}]
  (let [on-confirm (or on-confirm #())
        on-cancel (or on-cancel #())]
    (show-message-box (clj->js {:type "warning"
                                :title title
                                :message message
                                :buttons (clj->js ["确认" "取消"])})
                      (fn [response]
                        (if (= response 0)
                          (on-confirm)
                          (on-cancel))))))

(defn open-directory [{:keys [callback]}]
  (show-open-dialog (clj->js {:properties ["openDirectory"]})
                    callback))
