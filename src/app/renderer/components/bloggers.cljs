(ns app.renderer.components.bloggers
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.electorn :refer [dialog]]
            [app.renderer.components.icon :refer [icon]]))

(defstylesheet styles
  [.warpper {:color "#2f3235"}]
  [.blog-warpper {:padding "10px 12px"
                  :display "flex"
                  :border-bottom "1px solid #ebebeb"}]
  [.blog-warpper:hover {:padding-left "8px"
                        :border-left "4px solid #dd4c4f"}]
  [.blog-info {:flex-grow "4"}]
  [.blog-title {:margin "0"
                :background "#fafafa"
                :font-size "18px"
                :font-weight "normal"}]
  [.blog-description {:margin "0"
                      :font-size "14px"}]
  [.blog-date {:font-size "12px"}]
  [.blog-ctrl {:flex-grow "1"
               :padding "10px 5px"
               :display "flex"
               :justify-content "flex-end"}]
  [.blog-ctrl-icon {:font-size "18px"
                    :margin "0 5px"
                    :color "#666"
                    :cursor "pointer"}]
  [.blog-ctrl-icon:hover {:color "#dd4c4f"}])

(defn edit-blog [evt]
  (.stopPropagation evt)
  (js/console.log evt))

(defn delete-blog [evt blog]
  (let [title (get blog "title")]
    (.stopPropagation evt)
    (.showMessageBox dialog (clj->js {:type "warning"
                                      :title "删除博客"
                                      :message (str "确认删除" title "？")}))))

(defn blog-item [blog]
  (let [title (get blog "title")
        description (get blog "description")]
    [:div {:class blog-warpper}
     [:div {:class blog-info}
      [:h2 {:class blog-title} title]
      [:p {:class blog-description} description]
      [:time {:class blog-date} "2017 年 10 月 03 日更新"]]
     [:div {:class blog-ctrl}
      [icon {:type "edit"
             :class blog-ctrl-icon
             :on-click #(edit-blog %)}]
      [icon {:type "delete"
             :class blog-ctrl-icon
             :on-click #(delete-blog % blog)}]]]))

(defn bloggers [blog-list]
  (let [bloggers @(rf/subscribe [:bloggers])]
    [:div {:class warpper}
     (for [blog bloggers]
       ^{:key (get blog "uuid")}
       [blog-item blog])]))
