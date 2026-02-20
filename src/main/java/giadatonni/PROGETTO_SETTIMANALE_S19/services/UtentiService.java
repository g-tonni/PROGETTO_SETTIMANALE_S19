package giadatonni.PROGETTO_SETTIMANALE_S19.services;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Prenotazione;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Ruolo;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.BadRequestException;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.NotFoundException;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.UtenteDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.repositories.PrenotazioniRepository;
import giadatonni.PROGETTO_SETTIMANALE_S19.repositories.UtentiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UtentiService {
    private final UtentiRepository utentiRepository;
    private final PasswordEncoder passwordEncoder;
    private final PrenotazioniRepository prenotazioniRepository;

    public UtentiService(UtentiRepository utentiRepository, PasswordEncoder passwordEncoder, PrenotazioniRepository prenotazioniRepository) {
        this.utentiRepository = utentiRepository;
        this.passwordEncoder = passwordEncoder;
        this.prenotazioniRepository = prenotazioniRepository;
    }

    public Utente saveUtente(UtenteDTO body){
        if (body.dataNascita().isAfter(LocalDate.now())) throw new BadRequestException("La data di nascita deve essere precedente alla data di oggi");
        if (this.utentiRepository.existsByEmail(body.email())) throw new BadRequestException("Esiste già un account con questa email");
        Ruolo ruolo;
        if (body.ruolo().equals("utente")){
            ruolo = Ruolo.UTENTE;
        } else if (body.ruolo().equals("organizzatore")) {
            ruolo = Ruolo.ORGANIZZATORE;
        } else {
            throw new BadRequestException("Il ruolo può essere organizzatore o utente");
        }
        Utente nuovoUtente = new Utente(body.nome(), body.cognome(), body.dataNascita(), body.email(), passwordEncoder.encode(body.password()), ruolo);
        this.utentiRepository.save(nuovoUtente);
        System.out.println("Utente salvato");
        return nuovoUtente;
    }

    public Utente findById (UUID utenteId){
        return this.utentiRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente findByEmail(String email){
        return this.utentiRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Email non trovata"));
    }

    public Page<Prenotazione> findPrenotazioniByUtente(UUID utenteId, int page, int size){
        if (size > 100) size = 100;
        if (size < 0) size = 0;
        if (page > 30) page = 30;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataPrenotazione"));
        return this.prenotazioniRepository.findAllByUtente(utenteId, pageable);
    }

    public Prenotazione findPrenotazioneById(UUID prenotazioneId){
        return this.prenotazioniRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public void deletePrenotazione(UUID prenotazioneId, UUID utenteId){
        Prenotazione prenotazione = this.findPrenotazioneById(prenotazioneId);
        Utente utente = this.findById(utenteId);
        if(!utente.getUtenteId().equals(prenotazione.getUtente().getUtenteId()))
            throw new BadRequestException("È possibile eliminare solo le proprie prenotazioni");
        this.prenotazioniRepository.delete(prenotazione);
        System.out.println("Prenotazione eliminata");
    }
}
