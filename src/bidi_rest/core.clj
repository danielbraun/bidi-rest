(ns bidi-rest.core)

(defn- resources [id]
  (let [action #(keyword (name id) (name %))]
    [(name id) {"" {:get (action :index)
                    :post (action :create)}
                "/new" {:get (action :new)}
                ["/" :id] {"" {:get (action :show)
                               :put (action :update)
                               :patch (action :update)
                               :delete (action :destroy)}
                           "/edit" {:get (action :edit)}}}]))

(defn- resource [id]
  (let [action #(keyword (name id) (name %))]
    [(name id) {"" {:delete (action :destroy)
                    :get (action :show)
                    :patch (action :update)
                    :post (action :create)
                    :put (action :update)}
                "/edit" {:get (action :edit)}
                "/new" {:get (action :new)}}]))
