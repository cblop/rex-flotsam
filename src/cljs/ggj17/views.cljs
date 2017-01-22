(ns ggj17.views
  (:require
   [goog.dom :as dom]
   [re-frame.core :as re-frame]
   [hickory.core :as h]
   [re-com.core :as re-com]))

(defn get-line []
  (let [d (re-frame/subscribe [:dialogue])
        character (:character @d)
        type (:type @d)
        id (:id @d)
        lines (re-frame/subscribe [:lines])]
    (if (= character :rex)
      (if (= type :question) (:question (first (filter #(= (:id %) id) (:questions @lines))))
          (:comment (first (filter #(= % id) (:comments @lines))))
          )
      (if (= type :answer) (:answer (first (filter #(= (:question %) id) (:questions (get (:answers lines) character)))))))))

;; (:answer (first (filter #(= (:question %) 0) (:questions (get (:answers lines) :sam)))))

;; (:question (first (filter #(= (:id %) 0) (:questions lines))))

;; (get-line :rex :question 0)
;; (get-line :luce :answer 0)

;; (defn handler [response])

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
        scene (re-frame/subscribe [:scene])
        svg (:file @scene)
        ]
    ;; [:svg (embed-svg @svg)]
    ;; [:svg @svg]
    [:div {:class "floatTL"
           }
     [:img {:src svg
            :width "100%"
            :height "auto"
            }]]

    ))

(defn object [thing]
    [:img {:class "floatTL object"
           :style {:top (:y thing)
                   :left (:x thing)}
           :src (:file thing)
           :width (:width thing)
           :on-click (:action thing)}])

(defn npc [char]
  [:img {:class "floatTL object"
         :style {:top (:y char)
                 :left (:x char)}
         :src (:body char)
         :width (:width char)
         :on-click #(re-frame/dispatch [:question (:name char)])}])

(defn get-answer [line]
  {:character :sam
   :type :answer
   :file "characters/sam-face.svg"
   :id 0})

(defn dialogue [{:keys [character id]}]
  (let [chars (re-frame/subscribe [:characters])
        this-char (first (filter #(= (:name %) character) @chars))
        scene (re-frame/subscribe [:scene])
        other-char (first (:characters @scene))
        line (first (filter #(= (:id %) id) (:dialogue this-char)))
        nudge (:nudge line)
        callback #(do
                    (if nudge (re-frame/dispatch [:change-realness nudge]))
                    (re-frame/dispatch [:set-dialogue nil])
                    (if (and (= (:name this-char) :rex) other-char)
                      (js/setTimeout
                       (fn [] (do
                                (re-frame/dispatch [:say other-char (:id line)]))) 200))
                    )
        ]
    (if-not (nil? line)
      [:div {:class "dialogue"}
       [:img {:class "floatTL"
              :style {:top "5%"
                      :left (if (= (:name this-char) :rex) "5%" "47%")}
              :src (:face this-char)
              :width "50%"
              :on-click callback}]
       [:textarea {:class (if (= (:name this-char) :rex) "floatTL line-left" "floatTL line-right") :rows 10 :cols 28
                   :on-click callback
                   }
        (:line line)]])))

(defn exit-left []
  [:div {:class "exit-left"
         :on-click #(re-frame/dispatch [:change-scene :left])}])


(defn exit-right []
  [:div {:class "exit-right"
         :on-click #(re-frame/dispatch [:change-scene :right])}])

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

(defn get-questions [realness]
  (let [lines (re-frame/subscribe [:lines])]
    (filter #(= (:realness %) realness) (:questions @lines))))

(defn questions [qs]
  [:div {:class "floatTL"
         :style {:width "100%"}}
   (map (fn [q i]
          [re-com/button
           :label (:line q)
           :class "floatTL question-button"
           :style {:top (+ (* 50 i) 300)
                   :left "5%"
                   :width "90%"}
           :on-click #(do
                        (re-frame/dispatch [:no-questions])
                        (re-frame/dispatch [:comment (:id q)])
                        )]) qs (range (count qs)))
   ])


;; (defn backdrop
;;   (fn []
;;     (let [scene [re-frame/subscribe [:current-scene]]
;;           backdrop [re-frame/subscribe [:backdrop]]]
;;       )))

(defn get-object [name]
  (let [objects (re-frame/subscribe [:objects])]
    (first (filter #(= (:name %) name) @objects))))

(defn get-character [name]
  (let [characters (re-frame/subscribe [:characters])]
    (first (filter #(= (:name %) name) @characters))))

(defn main-panel []
  (fn []
    (let [scene-no (re-frame/subscribe [:scene-no])
          scene (re-frame/subscribe [:scene])
          qs (re-frame/subscribe [:questions])
          talking (re-frame/subscribe [:talking])
          characters (:characters @scene)
          d (re-frame/subscribe [:dialogue])
          objects (:objects @scene)]
      [re-com/v-box
       :height "100%"
       :children [
                  [backdrop]
                  ;; [scene]
                  [character]
                  (for [o objects]
                    [object (get-object o)])
                  (for [c characters]
                    [npc (get-character c)])
                  (if @d
                    [dialogue @d])
                  (if (seq @qs)
                    [questions @qs])
                  (if-not (= @scene-no 0)
                    [exit-left])
                  (if-not (= @scene-no 3)
                    [exit-right])
                  ;; [face]
                  ;; [dialogue]
                  ]])))
