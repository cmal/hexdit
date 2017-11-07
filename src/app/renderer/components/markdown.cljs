(ns app.renderer.components.markdown
  [:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [clojure.string :as string]
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

(defn- markdown-list []
  (let [blog-view @(rf/subscribe [:blog-view])
        lists (filter-by-query
                (if blog-view @(rf/subscribe [blog-view]) [])
                @search-query)]
    [:div {:class list-wrapper}
     (if (> (count lists) 0)
       (for [item lists]
         (let [data (get item "data")
               content (get item "content")
               title (get data "title")]
           ^{:key title}
           [:div {:class item-wrapper}
            [:h2 {:class item-title}
             title]
            [:p {:class item-content}
             content]]))
       (if (= @search-query "")
         [:span "正在加载..."]
         [:span "没有匹配的文章"]))]))

(defn markdown []
  [:div {:class wrapper}
   [search {:on-change change-search-query}]
   [markdown-list]])

