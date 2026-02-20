package giadatonni.PROGETTO_SETTIMANALE_S19.services;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Evento;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.BadRequestException;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.UnauthorizedException;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.EventoDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.repositories.EventiRepository;
import giadatonni.PROGETTO_SETTIMANALE_S19.security.JWTTools;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EventiService {

    private final EventiRepository eventiRepository;
    private final UtentiService utentiService;
    private final JWTTools jwtTools;

    public EventiService(EventiRepository eventiRepository, UtentiService utentiService, JWTTools jwtTools) {
        this.eventiRepository = eventiRepository;
        this.utentiService = utentiService;
        this.jwtTools = jwtTools;
    }

    public Evento saveEvento(EventoDTO body){

        Utente utente = this.utentiService.findById(body.organizzatoreId());

        if (body.data().isBefore(LocalDate.now())) throw new BadRequestException("La data dell'evento deve essere successiva alla data di oggi");

        Evento nuovoEvento = new Evento(body.titolo(), body.descrizione(), body.data(), body.luogo(), body.maxIngressi(), utente);

        this.eventiRepository.save(nuovoEvento);

        System.out.println("Evento salvato");

        return nuovoEvento;
    }
}
