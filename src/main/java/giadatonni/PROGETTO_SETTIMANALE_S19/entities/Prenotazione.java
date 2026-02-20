package giadatonni.PROGETTO_SETTIMANALE_S19.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue
    @Column(name = "prenotazione_id")
    private UUID prenotazioneId;

    @Column(name = "data_prenotazione", nullable = false)
    private LocalDate dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    public UUID getPrenotazioneId() {
        return prenotazioneId;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public Evento getEvento() {
        return evento;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "prenotazioneId=" + prenotazioneId +
                ", dataPrenotazione=" + dataPrenotazione +
                ", utente=" + utente +
                ", evento=" + evento +
                '}';
    }
}
