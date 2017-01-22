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
 :scene-no
 (fn [db]
   (:scene db)))

(re-frame/reg-sub
 :scene
 (fn [db]
   (nth (:scenes db) (:scene db))))

(re-frame/reg-sub
 :character
 (fn [db]
   (:character db)))


(re-frame/reg-sub
 :lines
 (fn [db]
   (:lines db)))

(re-frame/reg-sub
 :objects
 (fn [db]
   (:objects db)))

(re-frame/reg-sub
 :characters
 (fn [db]
   (:characters db)))

(re-frame/reg-sub
 :dialogue
 (fn [db]
   (:dialogue db)))

(re-frame/reg-sub
 :questions
 (fn [db]
   (:questions db)))


(re-frame/reg-sub
 :letterbox
 (fn [db]
   (:letterbox db)))


(re-frame/reg-sub
 :realness
 (fn [db]
   (:realness db)))


(re-frame/reg-sub
 :asking
 (fn [db]
   (:asking db)))


(re-frame/reg-sub
 :talking
 (fn [db]
   (:talking db)))
