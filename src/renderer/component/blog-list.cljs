(ns renderer.component.blog-list
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [secretary.core :as secretary]))

(def electron (js/require "electron"))
(def ipcRenderer (.-ipcRenderer electron))

(def blog-list (atom (js->clj (.sendSync ipcRenderer "get-blog-list"))))

(defn click-blog [blog-info]
  (rf/dispatch-sync [:switch-blog blog-info])
  (.send ipcRenderer "open-blog")
  (secretary/dispatch! "/blog"))

(defn remove-blog [idx]
  (let [new-blog-list (js->clj (.sendSync ipcRenderer "remove-blog" idx))]
    (reset! blog-list new-blog-list)))

(defn blog-control [idx]
  [:div {:class "blog-control"}
   [:i {:class "fa fa-pencil"}]
   [:i {:class "fa fa-trash"
        :on-click (fn [e]
                     (.stopPropagation e)
                     (remove-blog idx))}]])

(defn blog-list-component []
  [:div {:class "list-content"}
    (map-indexed
      (fn [idx blog-info]
        ^{:key (str idx (get blog-info "path"))}
        [ant/card {:class "blog-item"
                   :bodyStyle {:padding "15px"}
                   :noHovering true
                   :bordered false
                   :extra (reagent/as-element [blog-control idx])
                   :on-click #(click-blog blog-info)}
          [:h2 {:class "blog-title"} (get blog-info "title")]
          [:p {:class "blog-description"} (get blog-info "description")]])
      @blog-list)])
