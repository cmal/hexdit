(ns app.main.hexo)

(def fs       (js/require "fs"))
(def path     (js/require "path"))

(defn check-pkg [blog-path]
  (let [pkg-path (.join path blog-path "package.json")
        pkg (if (.existsSync fs pkg-path)
              (js->clj (.parse js/JSON (.readFileSync fs pkg-path "utf-8")))
              nil)]
    (not= (get pkg "hexo") nil)))
