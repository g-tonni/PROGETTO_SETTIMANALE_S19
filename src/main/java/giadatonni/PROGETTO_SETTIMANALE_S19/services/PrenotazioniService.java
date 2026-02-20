package giadatonni.PROGETTO_SETTIMANALE_S19.services;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Evento;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Prenotazione;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Ruolo;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.BadRequestException;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.PrenotazioneDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.repositories.PrenotazioniRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioniService {

    private final PrenotazioniRepository prenotazioniRepository;
    private final EventiService eventiService;
    private final UtentiService utentiService;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository, EventiService eventiService, UtentiService utentiService) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.eventiService = eventiService;
        this.utentiService = utentiService;
    }

    public Prenotazione postPrenotazione(PrenotazioneDTO body, UUID utenteId){

        Evento evento = this.eventiService.findById(body.eventoId());
        Utente utente = this.utentiService.findById(utenteId);

        if(utente.getRuolo().equals(Ruolo.ORGANIZZATORE)){
            if (evento.getOrganizzatore().getUtenteId().equals(utente.getUtenteId()))
                throw new BadRequestException("Non è possibile prenotare degli eventi di cui si è organizzatore");
        }

        List<Prenotazione> listaPrenotazioni = this.prenotazioniRepository.findByUtenteEdEvento(evento.getEventoId(), utente.getUtenteId());
        if(!listaPrenotazioni.isEmpty()) throw new BadRequestException("Non è possibile effettuare più di una prenotazione per lo stesso utente per lo stesso evento");

        Prenotazione nuovaPrenotazione = new Prenotazione(LocalDate.now(), utente, evento);

        this.prenotazioniRepository.save(nuovaPrenotazione);

        System.out.println("Prenotazione salvata");

        return nuovaPrenotazione;
    }
}
