(ns renderer.component.shortcuts
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [secretary.core :as secretary]))

(def electron (js/require "electron"))
(def shell (.-shell electron))

(defn switch-blog []
  (rf/dispatch-sync [:switch-blog nil])
  (secretary/dispatch! "/"))

(defn open-blog-folder []
  (let [folder-path (get @(rf/subscribe [:current-blog]) "path")]
    (.openExternal shell (str "file://" folder-path))))

(def shortcuts-option [{:icon "retweet"
                        :text "切换博客"
                        :action #(switch-blog)}
                       {:icon "folder"
                        :text "打开文件夹"
                        :action #(open-blog-folder)}
                       {:icon "link"
                        :text "本地预览"
                        :action #()}
                       {:icon "upload"
                        :text "上传部署"
                        :action #()}])

(defn shortcuts-component []
  [:div {:class "shortcuts"}
    (for [shortcut shortcuts-option]
      ^{:key (:icon shortcut)}
      [ant/tooltip {:placement "topLeft"
                    :title (:text shortcut)
                    :arrowPointAtCenter true
                    :autoAdjustOverflow true}
        [ant/icon {:class "icon"
                   :type (:icon shortcut)
                   :on-click (:action shortcut)}]])])

