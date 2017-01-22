(ns ggj17.db
  (:require [re-frame.core :as re-frame]))

(def lines
  {:rex [
         {:id 26
          :topic :foot
          :line "There's a severed foot on the beach. Clearly a murder has happened here."}
         {:id 0
          :topic :evidence
          :line "Detective Rex Flotsam, SBPD. Are you aware that a severed human foot was on the beach not far from here?"
          :realness 0
          }
         {:id 1
          :topic :murder
          :line "Detective Flotsam here. Are you aware that there was a murder here last night?"
          :realness 0}
         {:id 2
          :topic :victim
          :line "This foot must belong to someone. Who was killed last night?"
          :realness 0}
         {:id 3
          :topic :evidence
          :line "Why am I holding a shoe?"
          :realness 3}
         {:id 4
          :topic :victim
          :line "There was a murder last night. Did you know the victim?"
          :realness 1}
         {:id 5
          :topic :murder
          :line "I have a feeling something strange happened here last night. Do you know anything about it?"
          :realness 3}
         {:id 6
          :topic :evidence
          :line "Who does this shoe belong to?"
          :realness 4}
         {:id 7
          :topic :murder
          :line "My God. What did I do?"
          :realness 5}
         {:id 8
          :topic :evidence
          :line "There is something strange about this foot that I found on the beach..."
          :realness 2}
         {:id 9
          :topic :evidence
          :line "Strange thing to find on a beach, a foot. How do you think it got there?"
          :realness 1}
         {:id 10
          :topic :murder
          :line "What happened at the beach last night? Were there any witnesses?"
          :realness 1}
         {:id 11
          :topic :murder
          :line "Did you see anything suspicious happening here last night?"
          :realness 2}
         {:id 12
          :topic :victim
          :line "I don't see any corpses. There's only this severed foot. Have you seen anything?"
          :realness 1}
         {:id 13
          :topic :victim
          :line "I suspect there was a homicide, but I'm not sure about the motive."
          :realness 2}
         {:id 14
          :topic :victim
          :line "Though I'm not too sure what happened here last night, there was a crime, and there must have been a victim. Who was it?"
          :realness 3}
         {:id 15
          :topic :victim
          :line "I think... I think the murder had something to do with... getting a fix."
          :realness 4}
         {:id 16
          :topic :victim
          :line "Mary... what have I done?"
          :realness 5}
         {:id 17
          :topic :murder
          :line "Was there... a murder? I think... I think I remember something."
          :realness 4}
         {:id 18
          :topic :killer
          :line "I'm detective Rex Flotsam, Beach Cop. Did you kill someone on the beach last night?"
          :realness 0}
         {:id 19
          :topic :killer
          :line "I'm investigating a murder on behalf of Sunny Beach Police Department. Where were you last night?"
          :realness 1}
         {:id 20
          :topic :killer
          :line "I'm sure there was a killing here last night. I just need to know the motive, so I can track down the killer."
          :realness 2}
         {:id 21
          :topic :killer
          :line "I'm having trouble tracking down the killer."
          :realness 3}
         {:id 22
          :topic :killer
          :line "People keep telling me things about the killings. I don't want to believe them."
          :realness 4}
         {:id 23
          :topic :killer
          :line "The identity of the killer... they... I... Mary..."
          :realness 5}
         {:id 24
          :topic :evidence
          :line "Why was someone killed over a shoe? Was this shoe important somehow?"
          :realness 4}
         {:id 25
          :topic :evidence
          :line "The shoe... she was hiding the stash in the shoe..."
          :realness 5}
         ]
   :adam [{:id -1
     :line "I wouldn't know about that."
     :nudge 0}
    {:id 0
     :line "What are you talking about? What severed foot?"
     :nudge 1}
    {:id 1
     :line "I was around, but didn't see any murders. And I don't have to answer your questions!"
     :nudge 0}
    {:id 2
     :line "That's... that's not a foot, mate."
     :nudge 1}
    {:id 3
     :line "Why don't you ask the person who it used to belong to?"
     :nudge 1}
    {:id 4
     :line "No, officer. I don't know nobody around here."
     :nudge 0}
    {:id 5
     :line "You mean you don't know?"
     :nudge 1}
    {:id 6
     :line "Well mate, it's not mine."
     :nudge 0}
    {:id 7
     :line "This is a new low, even for you."
     :nudge 1}
    {:id 8
     :line "Look, I may not be your average businessman on the street, but I never done anything like cutting off limbs."
     :nudge 0}
    {:id 9
     :line "Well, it WOULD be strange if it really were a foot. You should take a closer look."
     :nudge 1}
    {:id 10
     :line "I didn't see anything, but I heard there were at least two people involved. I would think you'd know at least ONE of them."
     :nudge 1}
    {:id 11
     :line "No, officer."
     :nudge 0}
    {:id 12
     :line "I haven't seen the other one, if that's what you mean."
     :nudge 0}
    {:id 13
     :line "Mate, you are so far gone if you don't know what's going on here."
     :nudge 1}
    {:id 14
     :line "I had nothing to do with it. If you're not arresting me, I'm not talking."
     :nudge 0}
    {:id 15
     :line "Yeah, those damn junkies. Always killing each other for a fix. I gotta get out of this business..."
     :nudge 0}
    {:id 16
     :line "She should have left you while she had the chance. Now she's paid the price."
     :nudge 1}
    {:id 17
     :line "Your guess is as good as mine. Could have been a murder, yeah."
     :nudge 0}
    {:id 18
     :line "Not you again. You need to give up the fantasy, mate."
     :nudge 1}
    {:id 19
     :line "I was here, working. But I have an alibi."
     :nudge 0}
    {:id 20
     :line "Nothing to do with me. I ain't seen nothin'."
     :nudge 0}
    {:id 21
     :line "Maybe a little introspection would help."
     :nudge 1}
    {:id 22
     :line "Maybe you just don't want to face the truth."
     :nudge 1}
    {:id 23
     :line "I hope you rot in hell for what you did to her."
     :nudge 1}
    {:id 24
     :line "Maybe there was something inside it. Something you wanted."
     :nudge 1}
    {:id 25
     :line "...I see"
     :nudge 0}
    ]
   :sam
   [{:id -1
     :line "I don't know anything, man!"
     :nudge 0}
    {:id 0
     :line "Jesus. Stop acting like you're a cop. This is getting old."
     :nudge 1}
    {:id 1
     :line "Nope."
     :nudge 0}
    {:id 2
     :line "Dunno."
     :nudge 0}
    {:id 3
     :line "You should know. I mean, you knew its owner."
     :nudge 1}
    {:id 4
     :line "No, but you sure did."
     :nudge 1}
    {:id 5
     :line "Nope."
     :nudge 0}
    {:id 6
     :line "Not a clue."
     :nudge 0}
    {:id 7
     :line "You went too far this time. We've put up with you for too long on this beach. Damn junkies."
     :nudge 1}
    {:id 8
     :line "Foot? You mean that shoe you're holding?"
     :nudge 1}
    {:id 9
     :line "That's no foot."
     :nudge 1}
    {:id 10
     :line "Well, I wasn't here for a start."
     :nudge 0}
    {:id 11
     :line "Yeah. You were here, doing what you usually do."
     :nudge 1}
    {:id 12
     :line "Nope."
     :nudge 0}
    {:id 13
     :line "You mean you don't remember? Jesus, man..."
     :nudge 1}
    {:id 14
     :line "Not me."
     :nudge 0}
    {:id 15
     :line "Oh, so you're starting to remember?"
     :nudge 1}
    {:id 16
     :line "You killed her."
     :nudge 1}
    {:id 17
     :line "You're going to jail for good this time."
     :nudge 1}
    {:id 18
     :line "I can't believe how deluded you are. You honestly don't remember anything? You're a danger to society."
     :nudge 1}
    {:id 19
     :line "The real question is where YOU were last night."
     :nudge 1}
    {:id 20
     :line "I'm sure you won't be looking for long..."
     :nudge 1}
    {:id 21
     :line "That's surprising. I mean, it shouldn't be THAT hard..."
     :nudge 1}
    {:id 22
     :line "Uh huh."
     :nudge 0}
    {:id 23
     :line "Riiiiight."
     :nudge 0}
    {:id 24
     :line "She was hiding something from you in it."
     :nudge 1}
    {:id 25
     :line "..."
     :nudge 0}
    ]
  :luce  [{:id -1
           :line "Your guess is as good as mine."
           :nudge 0}
          {:id 0
           :line "No, officer, I don't know anything about it."
           :nudge 0}
          {:id 1
           :line "I heard something... a couple arguing, I heard."
           :nudge 1}
          {:id 2
           :line "I don't know anything about any killings, but I'm happy to answer any questions you have."
           :nudge 0}
          {:id 3
           :line "...weren't you saying that was a foot just now?"
           :nudge 1}
          {:id 4
           :line "No, officer. She wasn't anyone that I know."
           :nudge 0}
          {:id 5
           :line "There was a murder. A couple of junkies were arguing over something. It got ugly."
           :nudge 1}
          {:id 6
           :line "That was one of hers. The girl that got murdered."
           :nudge 1}
          {:id 7
           :line "..."
           :nudge 0}
          {:id 8
           :line "...are you sure that's not just a shoe?"
           :nudge 1}
          {:id 9
           :line "Yeah, a foot would be pretty strange. But that's no foot!"
           :nudge 1}
          {:id 10
           :line "I saw something. A couple of junkies arguing over a fix."
           :nudge 1}
          {:id 11
           :line "I saw the whole thing. Didn't see the face of the killer, though. He got away before they could catch him."
           :nudge 1}
          {:id 12
           :line "I saw them take the body away. It was a woman... say, didn't you know her?"
           :nudge 1}
          {:id 13
           :line "Sorry officer, I can't help you with that."
           :nudge 0}
          {:id 14
           :line "It was a young girl. Such a pity."
           :nudge 1}
          {:id 15
           :line "That's right. It was two junkies arguing over a fix. Their last stash, hidden inside a shoe."
           :nudge 1}
          {:id 16
           :line "..."
           :nudge 0}
          {:id 17
           :line "Did you see the girl get murdered as well? Were you there?"
           :nudge 1}
          {:id 18
           :line "Hello again! I didn't know you were a detective. You must have been doing undercover work before."
           :nudge 1}
          {:id 19
           :line "I was here officer. The same as every night..."
           :nudge 0}
          {:id 20
           :line "It was a young couple, arguing over something."
           :nudge 1}
          {:id 21
           :line "Don't worry officer, I'm sure you'll find him."
           :nudge 0}
          {:id 22
           :line "...did you have something to do with the killing? Did you know the girl involved?"
           :nudge 1}
          {:id 23
           :line "..."
           :nudge 0}
          {:id 24
           :line "Beats me, officer."
           :nudge 0}
          {:id 25
           :line "So it was you. You killed her, just to get to the stash she was hiding from you, trying to save you from the addiction that was turning you into someone else."
           :nudge 1}
          ]})

(def default-db
  {:name "Rex Flotsam: Beach Cop"
   :current-scene :beach
   :scenes [{:name :beach
             :characters []
             :objects [:foot]
             :file "backdrops/beach1.svg"}
            {:name :van
             :characters [:adam]
             :objects []
             :file "backdrops/van.svg"}
            {:name :bench
             :characters [:sam]
             :objects []
             :file "backdrops/bench.svg"}
            {:name :freeway
             :characters [:luce]
             :objects []
             :file "backdrops/freeway.svg"}
            ;; {:name :alley
            ;;  :characters []
            ;;  :objects []
            ;;  :file "backdrops/alley.svg"}
            ]
   :characters [{:name :rex
                 :body "characters/rex.svg"
                 :face "characters/rex-face.svg"
                 :dialogue (:rex lines)
                 :x "10%"
                 :y "50%"
                 :width "50%"}
                {:name :adam
                 :body "characters/adam.svg"
                 :face "characters/adam-face.svg"
                 :dialogue (:adam lines)
                 :x "70%"
                 :y "35%"
                 :width "11%"}
                {:name :sam
                 :body "characters/sam.svg"
                 :face "characters/sam-face.svg"
                 :dialogue (:sam lines)
                 :x "45%"
                 :y "47%"
                 :width "11%"}
                {:name :luce
                 :body "characters/luce.svg"
                 :face "characters/luce-face.svg"
                 :dialogue (:luce lines)
                 :x "65%"
                 :y "35%"
                 :width "7%"}
                ]
   :objects [{:name :foot
              :file "objects/foot.svg"
              :x "80%"
              :y "90%"
              :width "5%"
              :action #(re-frame/dispatch [:comment 26])}]
   :scene 0
   :realness 0
   :dialogue nil
   :questions []
   :asking false
   :talking false
   })

