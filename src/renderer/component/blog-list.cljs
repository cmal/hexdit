(ns renderer.component.blog-list
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [secretary.core :as secretary]))

(def electron (js/require "electron"))
(def ipcRenderer (.-ipcRenderer electron))

(def blog-list (.sendSync ipcRenderer "get-blog-list"))

(defn click-blog-item [blog]
  (rf/dispatch-sync [:switch-blog blog])
  (.send ipcRenderer "open-blog")
  (secretary/dispatch! "/blog"))

(defn blog-list-component []
  [:div {:class "list-content"}
    (map-indexed
      (fn [id blog-info]
        ^{:key (str id (.-path blog-info))}
        [ant/card {:class "blog-item"
                   :noHovering true
                   :bordered false
                   :on-click #(click-blog-item)}
          [:h2 {:class "blog-title"} (.-title blog-info)]
          [:p {:class "blog-description"} (.-description blog-info)]])
      blog-list)])
