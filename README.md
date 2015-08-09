# lumos-pedestal-component

A [component][1] for the [Pedestal][2].

[1]: https://github.com/stuartsierra/component
[2]: https://github.com/pedestal/pedestal

## Installation

Add the following dependency to your `project.clj`:

    [lumos-pedestal-component "0.1.0"]

## Usage

Require the library, and the Component library:

```clojure
(require '[lumos.component.pedestal :refer [pedestal-server]]
  '[io.pedestal.http :as pedestal]
  '[io.pedestal.http.route.definition :refer [defroutes]]
  '[com.stuartsierra.component :as component])
```

Then create a server component:

```clojure
(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello World"})

(defroutes routes
  [[["/" {:get handler}]]])

(def http-server
  (pedestal-server {:type :jetty :port 8080 :routes routes}))
```

## License

Copyright © 2015 Eunmin Kim

Distributed under the MIT License.
