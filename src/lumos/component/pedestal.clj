(ns lumos.component.pedestal
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as pedestal]))

(defrecord PedestalServer [router]
  component/Lifecycle
  (start [component]
    (if (:server component)
      component
      (let [options (assoc (dissoc component :router) :join? false)
            server (-> options
                     (assoc :routes (:routes router))
                     (#(into {} (for [[k v] %]
                                  [(keyword "io.pedestal.http" (name k)) v])))
                     pedestal/create-server
                     pedestal/start)]
        (assoc component :server server))))
  (stop [component]
    (if-let [server (:server component)]
      (do (pedestal/stop server)
          (dissoc component :server))
      component)))

(defn pedestal-server
  [options]
  (map->PedestalServer options))
