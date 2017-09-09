(defproject hexdit "0.1.0-SNAPSHOT"
  :license {:name "The MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :source-paths ["src"]
  :description "A hexo blog client build with electron and re-frame"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [figwheel "0.5.10"]
                 [reagent "0.6.1"]
                 [re-frame "0.10.1"]
                 [antizer "0.2.2"]
                 [secretary "1.2.3"]
                 [class-names "0.1.1"]
                 [binaryage/devtools "0.9.4"]
                 [ring/ring-core "1.6.1"]]
  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-less "1.7.5"]
            [lein-figwheel "0.5.10"]
            [lein-cooper "1.2.0"]]

  :clean-targets ^{:protect false} ["resources/main.js"
                                    "resources/public/js/renderer.js"
                                    "resources/public/js/renderer.js.map"
                                    "resources/public/js/renderer-out"
                                    "resources/public/css/main.css"]

  :less {:source-paths ["src/style"]
         :target-path "resources/public/css"}

  :cljsbuild
  {:builds
   [{:source-paths ["src/main"]
     :id "main-dev"
     :compiler {:output-to "resources/main.js"
                :output-dir "resources/public/js/main-out"
                :optimizations :simple
                :pretty-print true
                :cache-analysis true}}
    {:source-paths ["src/renderer" "config/dev"]
     :id "renderer-dev"
     :compiler {:output-to "resources/public/js/renderer.js"
                :output-dir "resources/public/js/renderer-out"
                :source-map true
                :asset-path "js/renderer-out"
                :optimizations :none
                :cache-analysis true
                :main "dev.core"}}
    {:source-paths ["src/main"]
     :id "main-release"
     :compiler {:output-to "resources/main.js"
                :output-dir "resources/public/js/main-release-out"
                :optimizations :advanced
                :pretty-print true
                :cache-analysis true
                :infer-externs true}}
    {:source-paths ["src/renderer"]
     :id "renderer-release"
     :compiler {:output-to "resources/public/js/renderer.js"
                :output-dir "resources/public/js/renderer-release-out"
                :source-map "resources/public/js/renderer.js.map"
                :optimizations :advanced
                :cache-analysis true
                :infer-externs true
                :main "renderer.core"}}]}
  :figwheel {:http-server-root "public"
             :css-dirs ["resources/public/css"]
             :ring-handler tools.figwheel-middleware/app
             :server-port 3449})
