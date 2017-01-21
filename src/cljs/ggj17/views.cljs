(ns ggj17.views
  (:require
   [goog.dom :as dom]
   [re-frame.core :as re-frame]
   [hickory.core :as h]
   [re-com.core :as re-com]))

(defn handler [response])

(defn embed-svg [svg-string]
  (let [parsed (h/parse svg-string)
        hiccup (h/as-hiccup parsed)]
    (println hiccup)
    hiccup))

(defn title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/h-box
       :justify :center
       :children
       [
        [re-com/title
         :label @name
         :level :level1]]])))

(defn backdrop []
  (let [window (dom/getWindow)
        viewport-size (dom/getViewportSize window)
        svg (re-frame/subscribe [:backdrop])
        ]
    ;; [:svg (embed-svg @svg)]
    ;; [:svg @svg]
    [:div {:class "floatTL"}
     [:img {:src "backdrops/beach1.svg"
            :width "100%"
            :height "100%"}]]
    ))


(defn character []
  (let [window (dom/getWindow)
        viewport-size (dom/getViewportSize window)
        svg (re-frame/subscribe [:character])
        ]
    ;; [:svg (embed-svg @svg)]
    [:div {:class "floatTL"
           :style {:top "50%"
                   :left "10%"}}
     (assoc-in (assoc-in @svg [1 :width] "100%") [1 :viewBox] "0 0 100% 100%")
     ;; @svg
     ]
    ))

(defn foot []
  (let [foot (re-frame/subscribe [:foot])]
    [:div {:class "floatTL"
           :style {:top "85%"
                   :left "80%"}
           :on-click #(js/alert "You pick up the foot")}
     (assoc-in (assoc-in @foot [1 :width] "100%") [1 :viewBox] "0 0 70% 70%")
     ;; @svg
     ])
  )


;; (defn backdrop
;;   (fn []
;;     (let [scene [re-frame/subscribe [:current-scene]]
;;           backdrop [re-frame/subscribe [:backdrop]]]
;;       )))

(defn main-panel []
  (fn []
    [re-com/v-box
     :height "100%"
     :children [
                [backdrop]
                [character]
                [foot]]]))
