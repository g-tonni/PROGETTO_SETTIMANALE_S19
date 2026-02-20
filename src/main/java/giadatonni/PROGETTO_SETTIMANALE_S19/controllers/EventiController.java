package giadatonni.PROGETTO_SETTIMANALE_S19.controllers;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Evento;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.EventoDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.services.EventiService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventiController {

    private final EventiService eventiService;

    public EventiController(EventiService eventiService) {
        this.eventiService = eventiService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento postEvento(@RequestBody @Validated EventoDTO body, @AuthenticationPrincipal Utente utenteAutenticato){
        return this.eventiService.saveEvento(body, utenteAutenticato.getUtenteId());
    }

    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    public Evento putEvento(@PathVariable UUID eventoId, @RequestBody @Validated EventoDTO body, @AuthenticationPrincipal Utente utenteAutenticato){
        return this.eventiService.putEvento(eventoId, body, utenteAutenticato.getUtenteId());
    }

    @DeleteMapping("/{eventoId}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable UUID eventoId, @AuthenticationPrincipal Utente utenteAutenticato){
        this.eventiService.deleteEvento(eventoId, utenteAutenticato.getUtenteId());
    }


}
