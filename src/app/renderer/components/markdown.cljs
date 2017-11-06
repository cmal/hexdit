(ns app.renderer.components.markdown
  [:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.components.search :refer [search]]])

(defstylesheet styles
  [.wrapper {:width "260px"
             :height "100vh"
             :position "fixed"
             :margin-left "180px"
             :background "#fafafa"
             :border-right "1px solid #dedede"
             :overflow-y "scroll"}]
  [.list-wrapper {:padding-top "50px"}]
  [.item-wrapper {:padding "10px 15px"
                  :border-bottom "1px solid #ebebeb"}]
  [.item-title   {:margin "0"
                  :color "#2f3235"
                  :font-size "17px"
                  :font-weight "normal"}]
  [.item-content {:display "-webkit-box"
                  :-webkit-box-orient "vertical"
                  :-webkit-line-clamp "3"
                  :overflow "hidden"
                  :margin "5px 0"
                  :font-size "12px"
                  :color "#a0a0a0"}])

(defn- markdown-list []
  (let [blog-view @(rf/subscribe [:blog-view])
        lists (if blog-view @(rf/subscribe [blog-view]) [])]
    [:div {:class list-wrapper}
     (for [item lists]
       (let [data (get item "data")
             content (get item "content")
             title (get data "title")]
         ^{:key title}
         [:div {:class item-wrapper}
          [:h2 {:class item-title}
           title]
          [:p {:class item-content}
           content]]))]))

(defn markdown []
  [:div {:class wrapper}
   [search]
   [markdown-list]])

