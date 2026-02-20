package giadatonni.PROGETTO_SETTIMANALE_S19.services;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Evento;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.BadRequestException;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.NotFoundException;
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

    public EventiService(EventiRepository eventiRepository, UtentiService utentiService) {
        this.eventiRepository = eventiRepository;
        this.utentiService = utentiService;
    }

    public Evento saveEvento(EventoDTO body, UUID utenteId){

        Utente utente = this.utentiService.findById(utenteId);

        if (body.data().isBefore(LocalDate.now())) throw new BadRequestException("La data dell'evento deve essere successiva alla data di oggi");

        Evento nuovoEvento = new Evento(body.titolo(), body.descrizione(), body.data(), body.luogo(), body.maxIngressi(), utente);

        this.eventiRepository.save(nuovoEvento);

        System.out.println("Evento salvato");

        return nuovoEvento;
    }

    public Evento findById(UUID eventoId){
       return this.eventiRepository.findById(eventoId).orElseThrow(() -> new NotFoundException(eventoId));
    }

    public Evento putEvento(UUID eventoId, EventoDTO body, UUID utenteId){
        Evento evento = this.findById(eventoId);
        Utente organizzatore = this.utentiService.findById(utenteId);
        if (!evento.getOrganizzatore().getUtenteId().equals(organizzatore.getUtenteId()))
            throw new BadRequestException("È possibile modificare solo gli eventi pubblicati dal proprio account");
        if (body.data().isBefore(LocalDate.now())) throw new BadRequestException("La data dell'evento deve essere successiva alla data di oggi");

        evento.setTitolo(body.titolo());
        evento.setDescrizione(body.descrizione());
        evento.setData(body.data());
        evento.setLuogo(body.luogo());
        evento.setMaxIngressi(body.maxIngressi());

        this.eventiRepository.save(evento);

        System.out.println("Evento aggiornato");

        return evento;
    }
}
