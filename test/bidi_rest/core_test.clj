(ns bidi-rest.core-test
  (:require [clojure.test :refer :all]
            [bidi-rest.core :refer :all]
            [bidi.bidi :as bidi]))

(def routes
  ["/" [(resources :stars)
        (resource :universe)]])

(defn- match-request [[method uri]]
  (:handler (bidi/match-route routes uri :request-method method)))

(deftest routes-test
  (are [handler request] (= handler (match-request request))
       :stars/create [:post "/stars"]
       :stars/destroy [:delete "/stars/1"]
       :stars/edit [:get "/stars/1/edit"]
       :stars/index [:get "/stars"]
       :stars/new [:get "/stars/new"]
       :stars/show [:get "/stars/1"]
       :stars/update [:patch "/stars/1"]
       :stars/update [:put "/stars/1"]
       :universe/create [:post "/universe"]
       :universe/destroy [:delete "/universe"]
       :universe/edit [:get "/universe/edit"]
       :universe/new [:get "/universe/new"]
       :universe/show [:get "/universe"]
       :universe/update [:patch "/universe"]
       :universe/update [:put "/universe"]))
