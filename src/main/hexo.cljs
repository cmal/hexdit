(ns main.hexo)

(def fs       (js/require "fs"))
(def path     (js/require "path"))
(def spawn    (.-spawn (js/require "child_process")))

(defn check-pkg [blog-path]
  (let [pkg-path (.join path blog-path "package.json")
        pkg (if (.existsSync fs pkg-path)
              (js->clj (.parse js/JSON (.readFileSync fs pkg-path "utf-8")))
              nil)]
    (not= (get pkg "hexo") nil)))

(defn preview [blog]
  (let [cwd  (aget blog "path")
        proc (spawn "hexo" (array "server") (js-obj "cwd" cwd))
        stdout ""
        stderr ""]
    (.on proc.stdout "data" (fn [data]
                              (js/console.log (.toString data))))))

