package giadatonni.PROGETTO_SETTIMANALE_S19.payloads;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PrenotazioneDTO (
        @NotBlank(message = "L'id dell'evento deve essere inserito")
        UUID eventoId
) {
}
