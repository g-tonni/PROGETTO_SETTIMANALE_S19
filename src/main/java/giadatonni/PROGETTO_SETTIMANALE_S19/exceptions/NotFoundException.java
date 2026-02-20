package giadatonni.PROGETTO_SETTIMANALE_S19.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("La risorsa con id " + id + " non è stata trovata");
    }
}
