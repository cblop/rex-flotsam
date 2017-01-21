(ns ggj17.db)

(def default-db
  {:name "Rex Flotsam: Beach Cop"
   :current-scene :beach
   :scenes {:beach {:file "resources/backdrops/beach1.svg"}}
   :characters {:rex {:file "resources/characters/rex.svg"
                      :x 0}}
   :objects {:foot {:file "resources/objects/foot.svg"}}
   })
