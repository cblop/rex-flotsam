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

(defn object [thing]
    [:img {:class "floatTL object"
           :style {:top (:y thing)
                   :left (:x thing)}
           :src (:file thing)
           :width (:width thing)
           :on-click (:action thing)}])


(defn dialogue []
  (let [d (re-frame/subscribe [:dialogue])
        ]
    (if-not (nil? @d)
      [:img {:class "floatTL"
             :style {:top "5%"
                     :left (if (= (:character @d) :rex) "5%" "47%")}
             :src (:file @d)
             :width "50%"
             :on-click #()}])))

(defn exit-left []
  [:div {:class "exit-left"}])


(defn exit-right []
  [:div {:class "exit-right"}])

(defn character []
  (let [character (re-frame/subscribe [:character])
        ]
    [:img {:class "floatTL"
           :style {:top "30%"
                   :left "10%"}
           :src @character
           :width "10%"}]

     ;; (assoc-in @svg [1 :style] {:width "100%" :padding-bottom "200%" :height "1px" :overflow "visible"})
    ;; [:svg (embed-svg @svg
    ;; [:div {:class "scaling-svg-container"
    ;;        :style {:top "50%"
    ;;                :left "50%"
    ;;                :width "300px"
    ;;                :padding-bottom "20%"}
    ;;        }
     ;; (assoc-in (assoc-in @svg [1 :width] "100px") [1 :viewBox] "0 0 100 100")
     ;; (assoc-in (assoc-in @svg [1 :preserve-aspect-ratio] "none") [1 :viewBox] "0 0 100 600")
     ;; (assoc-in (assoc-in @svg [1 :preserve-aspect-ratio] "none") [1 :width] "200px")
     ;; (assoc-in @svg [1 :width] "auto")
     ;; (assoc-in (assoc-in @svg [1 :class] "scaling-svg") [1 :viewBox] "0 0 50 300")
    ;;  ;; @svg
    ;;  ]
    ))

(defn foot []
  (let [foot (re-frame/subscribe [:foot])]
    [:div {:class "floatTL"
           :style {:top "85%"
                   :left "80%"}
           :on-click #(js/alert "You pick up the foot")}
     (assoc-in (assoc-in @foot [1 :width] "100%") [1 :viewBox] "0 0 70 70")
     ;; @svg
     ])
  )


(defn face []
  (let [foot (re-frame/subscribe [:foot])]
    [:div
     ])
  )


(defn scene []
  (let [objects (re-frame/subscribe [:objects])]
    [:div
     (for [o @objects]
       [object o])]
    ;; [object (first @objects)]
    ))


;; (defn backdrop
;;   (fn []
;;     (let [scene [re-frame/subscribe [:current-scene]]
;;           backdrop [re-frame/subscribe [:backdrop]]]
;;       )))

(defn main-panel []
  (fn []
    (let [objects (re-frame/subscribe [:objects])]
      [re-com/v-box
       :height "100%"
       :children [
                  [backdrop]
                  ;; [scene]
                  [character]
                  (for [o @objects]
                    [object o])
                  [dialogue]
                  [exit-left]
                  [exit-right]
                  ;; [face]
                  ;; [dialogue]
                  ]])))
