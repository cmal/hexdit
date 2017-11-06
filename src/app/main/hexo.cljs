(ns app.main.hexo)

(def fs       (js/require "fs"))
(def path     (js/require "path"))
(def matter   (js/require "gray-matter"))

(def blog-info (atom nil))

(defn check-pkg [blog]
  (let [blog-path (get blog "path")
        pkg-path (.join path blog-path "package.json")
        pkg (if (.existsSync fs pkg-path)
              (js->clj (.parse js/JSON (.readFileSync fs pkg-path "utf-8")))
              nil)]
    (not= (get pkg "hexo") nil)))

(defn load [js-blog]
  (let [blog (js->clj js-blog)]
    (reset! blog-info blog)))

(defn get-posts []
  (let [blog-path (get @blog-info "path")
        post-path (.join path blog-path "source" "_posts")
        files (js->clj (.readdirSync fs post-path))]
    (sort-by
      #(get (get % "data") "date")
      >
      (mapv
        (fn [file]
          (let [file-path (.join path post-path file)
                file-content (.toString (.readFileSync fs file-path))
                file-data (js->clj (matter file-content))]
            (merge file-data {"path" file-path})))
        files))))

