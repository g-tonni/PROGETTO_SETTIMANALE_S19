package giadatonni.PROGETTO_SETTIMANALE_S19.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record EventoDTO (
        @NotBlank(message = "Il titolo deve essere inserito")
        @Size(min = 2, max = 30, message = "Il titolo deve essere compreso tra i 2 e i 30 caratteri")
        String titolo,

        @NotBlank(message = "La descrizione deve essere inserito")
        @Size(min = 10, max = 500, message = "La descrizione deve essere compreso tra i 10 e i 500 caratteri")
        String descrizione,

        @Future(message = "La data dell'evento deve essere una data futura")
        LocalDate data,

        @NotBlank(message = "Il luogo deve essere inserito")
        @Size(min = 2, max = 30, message = "Il luogo deve essere compreso tra i 2 e i 30 caratteri")
        String luogo,

        @Min(value = 50, message = "Il numero di ingressi deve essere un minimo di 50")
        @Max(value = 1000, message = "Il numero di ingressi deve essere massimo di 1000")
        int maxIngressi,

        @NotBlank(message = "L'id dell'organizzatore deve essere inserito")
        UUID organizzatoreId
){
}
