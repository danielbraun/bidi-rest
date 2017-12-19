(defproject bidi-rest "0.1.0-SNAPSHOT"
  :description "Rails-like resourceful routing for Bidi"
  :url "https://github.com/danielbraun/bidi-rest"
  :cljsbuild {:builds []}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :profiles {:dev {:dependencies [[bidi "2.0.16"]]}})
