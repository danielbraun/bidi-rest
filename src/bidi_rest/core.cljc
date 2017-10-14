(ns bidi-rest.core)

;API is based on http://api.rubyonrails.org/classes/ActionDispatch/Routing/Mapper/Resources.html#method-i-resources

(defn- make-resource
  [singular? id & {:keys [children param path-names path]
                   :or {param :id
                        children []}}]
  (let [action #(keyword (name id) (name %))
        create [:post (action :create)]
        destroy [:delete (action :destroy)]
        edit [(get path-names :edit "edit") {:get (action :edit)}]
        index [:get (action :index)]
        new [(get path-names :new "new") {:get (action :new)}]
        show [:get (action :show)]
        update [[:patch (action :update)]
                [:put (action :update)]]]
    [(name (or path id))
     (if singular?
       [["" (into [destroy show create] update)]
        ["/" (into [edit new] children)]]
       [["" [index create]]
        ["/" [new]]
        [["/" (keyword param)] [["" (into [destroy show] update)]
                                ["/" [edit]]
                                ["/" children]]]])]))

(def resources (partial make-resource false))
(def resource (partial make-resource true))
