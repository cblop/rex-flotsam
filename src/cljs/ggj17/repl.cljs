(ns ggj17.repl
  (:require [re-frame.core :as re-frame]))

(def db (re-frame/subscribe [:db]))

(:character @db)
(:foot @db)
(:objects @db)
(keys @db)

(re-frame/dispatch [:set-character "characters/rex.svg"])
(re-frame/dispatch [:set-objects [{:name "foot"
                                   :file "objects/foot.svg"
                                   :x "80%"
                                   :y "70%"
                                   :width "7%"
                                   :action #(js/alert "Foot!")}]])


(re-frame/dispatch [:set-dialogue
                    {:character :rex
                     :file "characters/rex-face.svg"
                     :id 0}])

(re-frame/dispatch [:set-dialogue
                    {:character :sam
                     :file "characters/sam-face.svg"
                     :id 0}])

