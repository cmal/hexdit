(ns app.renderer.components.bloggers
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [secretary.core :as secretary]
            [forest.macros :refer-macros [defstylesheet]]
            [app.renderer.utils.modal :refer [confirm]]
            [app.renderer.components.icon :refer [icon]]
            [app.renderer.ipc :as ipc]))

(defstylesheet styles
  [.warpper {:color "#2f3235"
             :padding-top "70px"}]
  [.blog-warpper {:padding "10px"
                  :display "flex"
                  :background "#fafafa"
                  :border-left "4px solid #717b87"
                  :border-bottom "1px solid #ebebeb"}]
  [.blog-warpper:hover {:border-left "4px solid #dd4c4f"}]
  [.blog-info {:flex-grow "4"}]
  [.blog-title {:margin "0"
                :background "#fafafa"
                :font-size "18px"
                :font-weight "normal"}]
  [.blog-description {:margin "0"
                      :font-size "14px"}]
  [.blog-date {:font-size "12px"
               :color "#717b87"}]
  [.blog-ctrl {:flex-grow "1"
               :padding "10px 5px"
               :display "flex"
               :justify-content "flex-end"
               :align-items "flex-start"}]
  [.blog-ctrl-icon {:font-size "18px"
                    :padding "5px"
                    :color "#717b87"
                    :cursor "pointer"}]
  [.blog-ctrl-icon::before {:cursor "pointer"}]
  [.blog-ctrl-icon:hover {:color "#dd4c4f"}])

(defn edit-blog [evt blog]
  (.stopPropagation evt)
  (secretary/dispatch! (str "/edit?"
                            (secretary/encode-query-params blog))))

(defn delete-blog [evt blog]
  (let [title (get blog "title")
        uuid (get blog "uuid")]
    (.stopPropagation evt)
    (confirm {:title "删除博客"
              :message (str "确认删除" title "？")
              :on-confirm #(rf/dispatch-sync [:delete-blog uuid])})))

(defn open-blog [evt blog]
  (.stopPropagation evt)
  (rf/dispatch-sync [:current-blog blog])
  (secretary/dispatch! "/blog"))

(defn blog-item [blog]
  (let [title (get blog "title")
        description (get blog "description")]
    [:div {:class blog-warpper
           :on-click #(open-blog % blog)}
     [:div {:class blog-info}
      [:h2 {:class blog-title} title]
      [:p {:class blog-description} description]
      [:time {:class blog-date} "2017 年 10 月 03 日更新"]]
     [:div {:class blog-ctrl}
      [icon {:type "edit"
             :class blog-ctrl-icon
             :on-click #(edit-blog % blog)}]
      [icon {:type "delete"
             :class blog-ctrl-icon
             :on-click #(delete-blog % blog)}]]]))

(defn bloggers [blog-list]
  (let [bloggers @(rf/subscribe [:bloggers])]
    [:div {:class warpper}
     (for [blog bloggers]
       ^{:key (get blog "uuid")}
       [blog-item blog])]))
