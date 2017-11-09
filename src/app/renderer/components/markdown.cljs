(ns app.renderer.components.markdown
  [:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [clojure.string :as string]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.components.search :refer [search]]
            [app.renderer.components.spinner :refer [spinner]]])

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
                  :color "#a0a0a0"}]
  [.spinner-wrapper {:height "100vh"
                     :width "260px"
                     :padding "50px 0"
                     :display "flex"
                     :justify-content "center"
                     :align-items "center"
                     :flex-direction "column"}]
  [.spinner-text {:font-size "12px"
                  :color "#666"}])

(def search-query (reagent/atom ""))

(defn- change-search-query [str]
  (reset! search-query str))

(defn- filter-by-query [lists query]
  (filterv
    (fn [item]
      (let [title (get (get item "data") "title")
            content (get item "content")
            re-query (js/RegExp. query "i")]
        (or (re-find re-query title)
            (re-find re-query content))))
    lists))

(defn- markdown-list [lists]
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
         content]]))])

(defn loading-spinner []
  [:div {:class spinner-wrapper}
    [spinner {:size 40}]
    [:p {:class spinner-text}
     "正在加载..."]])

(defn markdown []
  (let [blog-view @(rf/subscribe [:blog-view])
        lists (if blog-view @(rf/subscribe [blog-view]) [])
        filter-lists (filter-by-query lists @search-query)]
    [:div {:class wrapper}
     [search {:on-change change-search-query}]
     (if (not= (count filter-lists) 0)
       [markdown-list filter-lists]
       (if (= @search-query "")
         [loading-spinner]))]))

