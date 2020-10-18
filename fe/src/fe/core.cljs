(ns fe.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defonce state
  (reagent/atom {:str1   ""
                 :str2   ""
                 :result nil}))

(defonce api-url "http://localhost:8080/api")

(defn scramble []
  (go (let [response (<! (http/post (str api-url "/scramble")
                                    {:with-credentials? false
                                     :json-params (select-keys @state [:str1 :str2])}))]
        (swap! state assoc :result
               (if (= (:status response) 200)
                 (str "Result: " (-> response :body :result))
                 (str "Error, server returned code " (:status response)))))))

(defn input [key title]
  [:input {:placeholder title
           :on-change   #(swap! state assoc key (-> % .-target .-value))}])

(defn root []
  [:div {:style {:display        "flex"
                 :flex-direction "column"
                 :align-items    "center"}}
   [:h1 "Scramble"]
   (input :str1 "String 1")
   (input :str2 "String 2")
   [:button {:on-click scramble} "Submit"]
   (when-let [result (:result @state)] [:span result])])

(defn start []
  (reagent/render-component [root]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
