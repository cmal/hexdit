(ns app.renderer.components.spinner
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]))

(defstylesheet styles
  [.wrapper {:position "relative"}]
             ; :display "inline-block"}]
  [.frame {:position "absolute"
           :background-color "#dd4c4f"
           :border-radius "100%"
           :opacity "0.6"
           :top "0"
           :left "0"
           :animation-fill-mode "both"}]
  [.frist-frame {:composes frame
                 :animation "bounce 2.1s 1s infinite ease-in-out"}]
  [.second-frame {:composes frame
                  :animation "bounce 2.1s 0s infinite ease-in-out"}])

(defn spinner [{:keys [size]}]
  (let [size-style {:height (str size "px")
                    :width (str size "px")}]
    [:div {:class wrapper
           :style size-style}
     [:div {:class frist-frame
            :style size-style}]
     [:div {:class second-frame
            :style size-style}]]))
