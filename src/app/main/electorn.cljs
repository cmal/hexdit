(ns app.main.electorn)

(def electron (js/require "electron"))
(def app (.-app electron))

;; BrowserWindow
(def browser-window (.-BrowserWindow electron))

(defn hide-window []
  (let [window (.getFocusedWindow browser-window)]
    ;; hide window but still can get window instance
    (.setSize window 1 1)
    (.setPosition window 0xFFFF 0xFFFF)))

(defn set-window-option [option]
  (let [window (.getFocusedWindow browser-window)
        width  (:width option)
        height (:height option)
        resizable (:resizable option)]
    (.setSize window width height)
    (.setResizable window resizable)
    (.center window)))

;; ipcMain
(def ipc-main (.-ipcMain electron))
(defn reg-ipc-event [ipcname ipcfn]
  (.on ipc-main (name ipcname) ipcfn))
