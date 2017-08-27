(ns renderer.component.blog-list
  (:require [reagent.core :as reagent]))

(def electron (js/require "electron"))
(def ipcRenderer (.-ipcRenderer electron))

(def blog-list (.sendSync ipcRenderer "get-blog-list"))

(defn blog-list-component []
  [:div {:class "list blog-list"}])
