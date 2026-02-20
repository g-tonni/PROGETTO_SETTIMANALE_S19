package giadatonni.PROGETTO_SETTIMANALE_S19.repositories;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventiRepository extends JpaRepository<Evento, UUID> {
}
