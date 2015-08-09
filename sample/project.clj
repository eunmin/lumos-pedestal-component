(defproject lumos-pedestal-component-sample "0.1.0-SNAPSHOT"
  :resource-paths ["config"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.stuartsierra/component "0.2.3"]
                 [io.pedestal/pedestal.immutant "0.4.0"]
                 [ch.qos.logback/logback-classic "1.1.2" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.7"]
                 [org.slf4j/jcl-over-slf4j "1.7.7"]
                 [org.slf4j/log4j-over-slf4j "1.7.7"]
                 [lumos-pedestal-component "0.1.0"]])
