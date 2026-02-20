package giadatonni.PROGETTO_SETTIMANALE_S19.controllers;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Prenotazione;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.services.UtentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtentiController {

    private final UtentiService utentiService;

    public UtentiController(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    @GetMapping("/me/prenotazioni")
    public Page<Prenotazione> findPrenotazioni(@AuthenticationPrincipal Utente utenteAutenticato,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size){
        return this.utentiService.findPrenotazioniByUtente(utenteAutenticato.getUtenteId(), page, size);
    }

    @DeleteMapping("/me/delete/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable UUID prenotazioneId, @AuthenticationPrincipal Utente utenteAutenticato){
        this.utentiService.deletePrenotazione(prenotazioneId, utenteAutenticato.getUtenteId());
    }
}
