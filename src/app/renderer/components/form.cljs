(ns app.renderer.components.form
  (:require [reagent.core :as reagent]
            [forest.macros :refer-macros [defstylesheet]]
            [secretary.core :as secretary]
            [app.renderer.utils.modal :as modal]))

(defstylesheet styles
  [.wrapper {:padding "85px 20px 0 15px"}]
  [.form-item {:margin-bottom "15px"}]
  [.label {:display "inline-block"
           :margin-bottom "5px"
           :font-size "14px"
           :color "#2f3235"}]
  [.input {:width "100%"
           :height "30px"
           :padding "4px 7px"
           :font-size "13px"
           :line-height "1.5"
           :color "rgba(0, 0, 0, .65)"
           :border "1px solid #d9d9d9"
           :border-radius "4px"}]
  [.input:focus {:border "1px solid #dd4c4f"
                 :box-shadow "0 0 3px rgba(221, 76, 79, .5)"}]
  [.textarea {:composes input
              :height "auto"}]
  [.button {:width "100%"
            :height "32px"
            :display "inline-block"
            :margin-bottom "10px"
            :line-height "1.15"
            :background "#fff"
            :border-radius "4px"
            :color "#2f3235"
            :border "1px solid #d9d9d9"
            :cursor "pointer"}]
  [.button:hover {:color "#dd4c4f"
                  :border "1px solid #dd4c4f"}])

(def fields (reagent/atom {:title ""
                           :path ""
                           :description ""}))

(defn- swap-fields [evt ky]
  (swap! fields assoc ky (-> evt .-target .-value)))

(defn- select-path [evt]
  (let [target (.-target evt)]
    (.blur target)
    (modal/open-directory
      {:callback
       (fn [paths]
         (let [path (first (js->clj paths))]
           (when-not (nil? path)
             (swap! fields assoc :path path))))})))

(defn- cancel [evt]
  (.stopPropagation evt)
  (secretary/dispatch! "/launcher"))

(defn- form-input [{:keys [id text on-focus]}]
  (let [on-focus (or on-focus #())]
    [:div {:class form-item}
     [:label {:class label :for id} text]
     [:input {:class input
              :id id
              :type "text"
              :value (@fields (keyword id))
              :on-focus #(on-focus %)
              :on-change #(swap-fields % (keyword id))}]]))

(defn- form-textarea [{:keys [id text rows in-input]}]
  [:div {:class form-item}
   [:label {:class label :for id} text]
   [:textarea {:class textarea
               :id id
               :rows rows
               :value (@fields (keyword id))
               :on-change #(swap-fields % (keyword id))}]])

(defn- form-button-grounp []
  [:div {:class form-item}
   [:button {:class button
             :on-click #(cancel %)}
    "取消"]
   [:button {:class button} "确认"]])

;; reagent lifecycle methods
(defn- will-mount [this value]
  (when-not (nil? value)
    (reset! fields value)))

(defn- render []
  [:form {:class wrapper}
   [form-input {:id "title"
                :text "博客名称"}]
   [form-input {:id "path"
                :text "博客路径"
                :on-focus #(select-path %)}]
   [form-textarea {:id "description"
                   :text "博客描述"
                   :rows 9}]
   [form-button-grounp]])

(defn form [value]
  (reagent/create-class
    {:component-will-mount #(will-mount % value)
     :reagent-render #(render)}))
