;test bed for SENG1110 Assignment1

#X:: ;WINDOWS KEY + X
Sleep, 250      ; Pause and ready
Send, tio{enter}
Sleep, 250      ; Pause and ready
Send, new_movie One1,Chris,1,1.1{enter}
Sleep, 250      ; 1s pause
Send, new_movie One2,New,4,4.3{enter}
Sleep, 250      ; 1s pause
Send, new_movie One3,New,4,4.3{enter}
Sleep, 250      ; 1s pause
Send, new_playlist One1,One3,One2{enter}
Sleep, 250      ; 1s pause
Send, list_playlist playlist1{enter}
Sleep, 250      ; 1s pause
Send, new_playlist One2,One1,One3{enter}
Sleep, 250      ; 1s pause
Send, list_playlist playlist2{enter}
Sleep, 250      ; 1s pause
Send, delete -mp One1,playlist1{enter}
Sleep, 250      ; 1s pause
Send, list_playlist playlist1{enter}
Sleep, 250      ; 1s pause
