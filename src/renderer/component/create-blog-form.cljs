(ns renderer.component.create-blog-form
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [antizer.reagent :as ant]
            [secretary.core :as secretary]))

(def electron (js/require "electron"))
(def dialog (.-dialog (.-remote electron)))

(defn cancel-create []
  (secretary/dispatch! "/"))

(defn select-blog-path [evt form]
  (let [target (.-target evt)
        open-dialog (.-showOpenDialog dialog)]
    (.blur target)
    (open-dialog (clj->js {:properties ["openDirectory"]})
                 (fn [paths]
                  (let [path (first (js->clj paths))]
                    (if-not (= path nil)
                      (do
                        (aset target "value" path)
                        (ant/set-fields-value form (clj->js {:path path})))))))))

(defn submit-form [form]
  (ant/validate-fields form
    (fn [error value]
      (if (= error nil)
        (do
          (rf/dispatch-sync [:add-blog value])
          (secretary/dispatch! "/")
          (ant/message-success "添加成功"))))))

(defn create-form []
  (fn [props]
    (let [form (ant/get-form)]
      [ant/form {:class "create-blog-form"}
        [ant/form-item {:label "博客名称"}
          (ant/decorate-field form "title" {:rules [{:required true
                                                    :message "必须，请填写博客名称"}]}
            [ant/input {:size "large"
                        :autoFocus true
                        :prefix (reagent/as-element
                                  [ant/icon {:type "book"}])}])]
        [ant/form-item {:label "博客路径"}
          (ant/decorate-field form "path" {:rules [{:required true
                                                    :message "必须，请选择博客路径"}]}
            [ant/input {:size "large"
                        :prefix (reagent/as-element
                                  [ant/icon {:type "folder"}])
                        :on-focus #(select-blog-path % form)}])]
        [ant/form-item {:label "博客描述"}
          (ant/decorate-field form "description" {:rules [:type "text"]}
            [ant/input-text-area {:rows 5}])]
        [ant/form-item
          [:div {:class "create-button-group"}
            [ant/button {:on-click #(cancel-create)}
              "返回"]
            [ant/button {:type "primary"
                         :on-click #(submit-form form)}
              "确认"]]]])))

(defn create-blog-form-component []
   (ant/create-form (create-form)))
