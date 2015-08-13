(ns lumos.component.pedestal-test
  (:import java.net.ConnectException)
  (:require [clojure.test :refer :all]
            [com.stuartsierra.component :as component]
            [lumos.component.pedestal :refer :all]
            [io.pedestal.http :as pedestal]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ring.util.response :refer [response]]
            [clj-http.client :as http]))

(defn test-handler
  [request]
  (response "test"))

(defroutes routes
  [[["/" {:get test-handler}]]])

(defn start-server-test [server]
  (testing "server start"
    (let [server (component/start server)]
      (try
        (let [response (http/get "http://127.0.0.1:8090/")]
          (is (= (:status response) 200))
          (is (= (:body response) "test")))
        (finally
          (component/stop server))))))

(defn stop-server-test [server]
  (testing "server stops"
    (is (thrown? ConnectException (http/get "http://127.0.0.1:8090/")))))

(deftest test-pedestal-jetty-server-test
  (let [server (pedestal-server {:type :jetty
                                 :port 8090
                                 :routes {:routes routes}})]
    (start-server-test server)
    (stop-server-test server)))

(deftest test-pedestal-immutant-server-test
  (let [server (pedestal-server {:type :immutant
                                 :port 8090
                                 :routes {:routes routes}})]
    (start-server-test server)
    (stop-server-test server)))
