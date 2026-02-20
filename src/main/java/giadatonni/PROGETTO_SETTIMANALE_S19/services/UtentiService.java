package giadatonni.PROGETTO_SETTIMANALE_S19.services;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Ruolo;
import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.BadRequestException;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.UtenteDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.repositories.UtentiRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UtentiService {
    private final UtentiRepository utentiRepository;
    private final PasswordEncoder passwordEncoder;

    public UtentiService(UtentiRepository utentiRepository, PasswordEncoder passwordEncoder) {
        this.utentiRepository = utentiRepository;
        this.passwordEncoder = passwordEncoder;
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
}
