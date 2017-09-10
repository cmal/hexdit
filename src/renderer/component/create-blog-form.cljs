(ns renderer.component.create-blog-form
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]))

(defn create-form []
  (fn [props]
    (let [form (ant/get-form)]
      [ant/form {:class "create-blog-form"}
        [ant/form-item {:label "博客名称"}
          (ant/decorate-field form "name" {:rules [{:required true} {:type "text"}]}
            [ant/input {:prefix (reagent/as-element
                                  [ant/icon {:type "book"}])}])]
        [ant/form-item {:label "博客路径"}
          (ant/decorate-field form "path" {:rules [{:required true} {:type "text"}]}
            [ant/input {:prefix (reagent/as-element
                                  [ant/icon {:type "folder"}])}])]
        [ant/form-item {:label "博客描述"}
          (ant/decorate-field form "description" {:rules [:type "text"]}
            [ant/input-text-area {:rows 4}])]])))

(defn create-blog-form-component []
   (ant/create-form (create-form)))
