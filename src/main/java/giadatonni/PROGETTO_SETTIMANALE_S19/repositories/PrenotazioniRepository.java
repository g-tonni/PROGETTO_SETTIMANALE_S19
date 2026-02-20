package giadatonni.PROGETTO_SETTIMANALE_S19.repositories;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {

    @Query("SELECT p FROM Prenotazione p WHERE p.utente.utenteId = :utenteId AND p.evento.eventoId = :eventoId")
    List<Prenotazione> findByUtenteEdEvento(UUID eventoId, UUID utenteId);

    @Query("SELECT p FROM Prenotazione p WHERE p.evento.eventoId = :eventoId")
    List<Prenotazione> findAllByEvento(UUID eventoId);
}
