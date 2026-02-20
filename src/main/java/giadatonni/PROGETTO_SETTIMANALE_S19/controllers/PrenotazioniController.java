package giadatonni.PROGETTO_SETTIMANALE_S19.controllers;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Prenotazione;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.PrenotazioneDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.services.PrenotazioniService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenota")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @PostMapping
    @ResponseStatus
    public Prenotazione postPrenotazione(@RequestBody PrenotazioneDTO body, @AuthenticationPrincipal Utente utenteAutenticato){
        return this.prenotazioniService.postPrenotazione(body, utenteAutenticato.getUtenteId());
    }
}
