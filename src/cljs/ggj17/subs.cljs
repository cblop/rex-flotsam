(ns ggj17.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :db
 (fn [db]
   db))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))


(re-frame/reg-sub
 :backdrop
 (fn [db]
   (:backdrop db)))


(re-frame/reg-sub
 :character
 (fn [db]
   (:character db)))

(re-frame/reg-sub
 :objects
 (fn [db]
   (:objects db)))

(re-frame/reg-sub
 :dialogue
 (fn [db]
   (:dialogue db)))
