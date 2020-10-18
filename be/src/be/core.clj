(ns be.core
  (:require [be.utils :as utils]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response file-response]]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(defonce port 8080)

(defroutes app-routes
           (GET "/" [] (file-response "index.html" {:root "resources/public"}))
           (POST "/api/scramble" {{:keys [str1 str2]} :body}
             (response {:result (utils/scramble? str1 str2)}))

           (route/resources "/")
           (route/not-found "Page not found"))

(def app
  (-> app-routes
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])
      (wrap-json-body {:keywords? true})
      wrap-json-response))

(defn -main
  [& args]
  (do (println (str "Starting server on port :" port))
      (jetty/run-jetty #'app {:port port, :join? true})))
