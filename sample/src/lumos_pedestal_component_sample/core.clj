(ns lumos-pedestal-component-sample.core
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as pedestal]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [lumos.component.pedestal :refer [pedestal-server]]))

(defn hello-world-handler
  [request]
  {:status 200 :headers {} :body "Hello World"})

(defroutes routes
  [[["/" {:get hello-world-handler}]]])

(def system (component/system-map
             :http-server (pedestal-server {:type :immutant
                                            :port 8080
                                            :routes routes})))

(defn -main [& args]
  (component/start system))


