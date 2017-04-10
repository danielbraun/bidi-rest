(ns bidi-rest.core)

(defn- make-resource [singular? id & {:keys [children param path]
                                      :or {param :id}}]
  (let [action #(keyword (name id) (name %))
        create [:post (action :create)]
        destroy [:delete (action :destroy)]
        edit ["/edit" {:get (action :edit)}]
        index [:get (action :index)]
        new ["/new" {:get (action :new)}]
        show [:get (action :show)]
        update [[:patch (action :update)]
                [:put (action :update)]]]
    [(name (or path id))
     (if singular?
       [["" (into [destroy show create] update)]
        edit
        new]
       [["" [index create]]
        new
        [["/" (keyword param)] [["" (into [destroy show] update)]
                                edit
                                ["/" children]]]])]))

(def resources (partial make-resource false))
(def resource (partial make-resource true))
