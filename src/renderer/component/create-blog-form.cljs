(ns renderer.component.create-blog-form
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [secretary.core :as secretary]))

(defn cancel-create []
  (secretary/dispatch! "/"))

(defn create-form []
  (fn [props]
    (let [form (ant/get-form)]
      [ant/form {:class "create-blog-form"}
        [ant/form-item {:label "博客名称"}
          (ant/decorate-field form "name" {:rules [{:required true} {:type "text"}]}
            [ant/input {:size "large"
                        :prefix (reagent/as-element
                                  [ant/icon {:type "book"}])}])]
        [ant/form-item {:label "博客路径"}
          (ant/decorate-field form "path" {:rules [{:required true} {:type "text"}]}
            [ant/input {:size "large"
                        :prefix (reagent/as-element
                                  [ant/icon {:type "folder"}])}])]
        [ant/form-item {:label "博客描述"}
          (ant/decorate-field form "description" {:rules [:type "text"]}
            [ant/input-text-area {:rows 4}])]
        [ant/form-item
          [:div {:class "create-button-group"}
            [ant/button {:on-click #(cancel-create)}
              "返回"]
            [ant/button {:type "primary"}
              "确认"]]]])))

(defn create-blog-form-component []
   (ant/create-form (create-form)))
