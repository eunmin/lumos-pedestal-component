# lumos-pedestal-component

A [component][1] for the [Pedestal][2].

[1]: https://github.com/stuartsierra/component
[2]: https://github.com/pedestal/pedestal

## Installation

Add the following dependency to your `project.clj`:

    [lumos-pedestal-component "0.1.1"]

## Usage

Require the library, and the Component library:

```clojure
(require '[lumos.component.pedestal :refer [pedestal-server]]
  '[io.pedestal.http.route.definition :refer [defroutes]]
  '[com.stuartsierra.component :as component])
```

Then create a server component:

```clojure
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
```

## License

Copyright © 2015 Eunmin Kim

Distributed under the MIT License.
