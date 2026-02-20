package giadatonni.PROGETTO_SETTIMANALE_S19.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO (
        @NotBlank(message = "L'email deve essere inserita")
        @Email(message = "Indirizzo email non valido")
        String email,
        @NotBlank(message = "La password deve essere inserita")
        @Size(min = 8, max = 30, message = "La password deve essere compresa tra gli 8 e i 30 caratteri")
        String password) {
}
