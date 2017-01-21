(ns ggj17.repl
  (:require [re-frame.core :as re-frame]))

(def db (re-frame/subscribe [:db]))

(:character @db)
(keys @db)

(re-frame/dispatch [:character "characters/rex.svg"])

