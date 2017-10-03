(ns app.renderer.components.icon
  (:require [reagent.core :as reagent]))

(defn icon [props]
  (let [icon-class (str (:class props)" zmdi zmdi-" (:type props))
        click-hander (:on-click props)]
    [:i {:class icon-class
         :on-click click-hander}]))

