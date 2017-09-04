(ns renderer.component.blog-list
  (:require [reagent.core :as reagent]))

(def electron (js/require "electron"))
(def ipcRenderer (.-ipcRenderer electron))

(def blog-list (.sendSync ipcRenderer "get-blog-list"))

(defn blog-list-component []
  [:ul {:class "list blog-list"}
    (map-indexed
     (fn [idx blog]
       ^{:key (str idx (.-title blog) (.-path blog))}
       [:li {:class "blog-item"}
         [:h2 {:class "blog-title"} (.-title blog)]
         [:p {:class "blog-description"} (.-description blog)]])
     blog-list)])
