(ns renderer.component.blog-list
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [secretary.core :as secretary]))

(def electron (js/require "electron"))
(def ipcRenderer (.-ipcRenderer electron))

(def blog-list (.sendSync ipcRenderer "get-blog-list"))

(defn click-blog-item [blog]
  (rf/dispatch-sync [:switch-blog blog])
  (.send ipcRenderer "open-blog")
  (secretary/dispatch! "/blog"))

(defn blog-list-component []
  [:ul {:class "list blog-list"}
    (map-indexed
     (fn [id blog]
       ^{:key (str id (.-path blog))}
       [:li {:class "blog-item"
             :on-click #(click-blog-item blog)}
         [:h2 {:class "blog-title"} (.-title blog)]
         [:p {:class "blog-description"} (.-description blog)]])
     blog-list)])
