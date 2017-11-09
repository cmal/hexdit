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

(defn get-pages []
  (let [blog-path (get @blog-info "path")
        page-path (.join path blog-path "source")
        files (filterv
                (fn [file-path]
                  (not (re-find #"_post" file-path))
                  (and (not (re-find #"_post" file-path))
                       (.existsSync fs (.join path file-path "index.md"))))
                (mapv
                  (fn [file-name]
                    (.join path page-path file-name))
                  (js->clj (.readdirSync fs page-path))))]
    (mapv
      (fn [file]
        (let [file-path (.join path file "index.md")
              file-content (.toString (.readFileSync fs file-path))
              file-data (js->clj (matter file-content))]
          (merge file-data {"path" file-path})))
      files)))
