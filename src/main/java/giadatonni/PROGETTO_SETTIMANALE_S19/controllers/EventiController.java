package giadatonni.PROGETTO_SETTIMANALE_S19.controllers;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Evento;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.EventoDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.services.EventiService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventi")
public class EventiController {

    private final EventiService eventiService;

    public EventiController(EventiService eventiService) {
        this.eventiService = eventiService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    public Evento postEvento(@RequestBody @Validated EventoDTO body){
        return this.eventiService.saveEvento(body);
    }
}
