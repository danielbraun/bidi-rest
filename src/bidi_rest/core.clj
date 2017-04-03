(ns bidi-rest.core)

(defn resources [id & {:keys [children param path]
                       :or {param :id}}]
  (let [action #(keyword (name id) (name %))]
    [(name (or path id)) {"" {:get (action :index)
                              :post (action :create)}
                          "/new" {:get (action :new)}
                          ["/" param] [["" {:get (action :show)
                                            :put (action :update)
                                            :patch (action :update)
                                            :delete (action :destroy)}]
                                       ["/edit" {:get (action :edit)}]
                                       ["/" children]]}]))

(defn resource [id & {:keys [path]}]
  (let [action #(keyword (name id) (name %))]
    [(name (or path id)) {"" {:delete (action :destroy)
                              :get (action :show)
                              :patch (action :update)
                              :post (action :create)
                              :put (action :update)}
                          "/edit" {:get (action :edit)}
                          "/new" {:get (action :new)}}]))
