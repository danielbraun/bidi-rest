(ns bidi-rest.core-test
  (:require [clojure.test :refer :all]
            [bidi-rest.core :refer :all]
            [bidi.bidi :as bidi]))

(def routes
  ["/" [(resources :planets)
        (resources :planets :path "planetas")
        (resources :stars :param :star_id)
        (resource :universe)
        (resource :universe :path "universo")]])

(defn- match-request [[method uri]]
  (bidi/match-route routes uri :request-method method))

(deftest routes-test
  (are [handler request] (= handler (:handler (match-request request)))
    :planets/create [:post "/planets"]
    :planets/destroy [:delete "/planets/1"]
    :planets/edit [:get "/planets/1/edit"]
    :planets/index [:get "/planets"]
    :planets/new [:get "/planets/new"]
    :planets/show [:get "/planets/1"]
    :planets/update [:patch "/planets/1"]
    :planets/update [:put "/planets/1"]
    :universe/create [:post "/universe"]
    :universe/destroy [:delete "/universe"]
    :universe/edit [:get "/universe/edit"]
    :universe/new [:get "/universe/new"]
    :universe/show [:get "/universe"]
    :universe/update [:patch "/universe"]
    :universe/update [:put "/universe"]
    :universe/show [:get "/universo"]
    :planets/index [:get "/planetas"])
  (is (= "3" (get-in (match-request [:get "/stars/3"])
                     [:route-params :star_id]))))
