(ns ggj17.events
    (:require [re-frame.core :as re-frame]
              [ajax.core :refer [GET POST]]
              [hickory.core :as h]
              [ggj17.db :as db]))

(defn get-character [response]
  (let [parsed (h/parse response)
        hiccup (h/as-hiccup parsed)]
    (println "RESPONSe:")
    ;; (println (nth (nth (nth hiccup 1) 3) 2))
    (println (nth (nth (first hiccup) 3) 2))
    ;; (println (nth (nth (second hiccup) 3) 2))
    ;; (re-frame/dispatch [:set-backdrop "hello"])
    (re-frame/dispatch [:set-character (nth (nth (first hiccup) 3) 2)])
    ))


(defn get-foot [response]
  (let [parsed (h/parse response)
        hiccup (h/as-hiccup parsed)]
    (println "RESPONSe:")
    ;; (println (nth (nth (nth hiccup 1) 3) 2))
    (println (nth (nth (first hiccup) 3) 2))
    ;; (println (nth (nth (second hiccup) 3) 2))
    ;; (re-frame/dispatch [:set-backdrop "hello"])
    (re-frame/dispatch [:set-foot (nth (nth (first hiccup) 3) 2)])
    ))

(re-frame/reg-event-db
 :comment
 (fn [db [_ id]]
   (re-frame/dispatch [:set-dialogue {:character :rex :id id}])
   db))

(re-frame/reg-event-db
 :say
 (fn [db [_ char id]]
   (re-frame/dispatch [:set-dialogue {:character char :id id}])
   db))

(re-frame/reg-event-db
 :show-line
 (fn [db [_ character id]]
   (assoc db :talking true :dialogue )))


(defn get-questions [player realness]
  (filter #(= (:realness %) realness) (:dialogue player)))

(re-frame/reg-event-db
 :no-questions
 (fn [db _]
   (assoc db :questions [])))

;; (re-frame/reg-event-db
;;  :player-ask
;;  (fn [db [_ id]]
;;    ))

(re-frame/reg-event-db
 :question
 (fn [db [_ char]]
   (let [realness (:realness db)
         player (first (filter #(= (:name %) :rex) (:characters db)))
         qs (get-questions player realness)]
     (assoc db :questions qs))))

(re-frame/reg-event-db
 :ask-questions
 (fn [db _]
   (assoc db :asking true)))

(re-frame/reg-event-db
 :change-scene
 (fn [db [_ direction]]
   (assoc db :scene (if (= direction :left) (dec (:scene db)) (inc (:scene db))))))

(re-frame/reg-event-db
 :set-asking
 (fn [db [_ val]]
   (assoc db :asking val)))

(re-frame/reg-event-db
 :set-talking
 (fn [db [_ val]]
   (assoc db :talking val)))

(re-frame/reg-event-db
 :set-character
 (fn [db [_ svg]]
   (assoc db :character svg)))

(re-frame/reg-event-db
 :set-dialogue
 (fn [db [_ dialogue]]
   (assoc db :dialogue dialogue)))

(re-frame/reg-event-db
 :set-objects
 (fn [db [_ os]]
   (assoc db :objects os)))


(re-frame/reg-event-db
 :set-foot
 (fn [db [_ svg]]
   (assoc db :foot svg)))

;; (defmacro embed-svg [svg]
;;   (let [parsed (h/parse svg)
;;         hiccup (h/as-hiccup parsed)]
;;     `~hiccup))

(defn scene-handler [response item]
  (re-frame/dispatch [:backdrop response]))

(re-frame/reg-event-db
 :character
 (fn [db [_ svg]]
   (GET svg {:response-format :raw
             :handler get-character})
   db))


(re-frame/reg-event-db
 :foot
 (fn [db [_ svg]]
   (GET svg {:response-format :raw
             :handler get-foot})
   db))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))


(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :get-backdrops
 (fn [db _]
   (GET "backdrops/beach1.svg" {:handler #(scene-handler % :beach)})))
