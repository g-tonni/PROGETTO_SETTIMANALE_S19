package giadatonni.PROGETTO_SETTIMANALE_S19.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UtenteDTO (
    @NotBlank(message = "Il nome deve essere inserito")
    @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra i 2 e i 30 caratteri")
    String nome,

    @NotBlank(message = "Il cognome deve essere inserito")
    @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra i 2 e i 30 caratteri")
    String cognome,

    @Past(message = "La data di nascita deve essere una data passata")
    LocalDate dataNascita,

    @NotBlank(message = "L'email deve essere inserita")
    @Email(message = "Indirizzo email non valido")
    String email,

    @NotBlank(message = "La password deve essere inserita")
    @Size(min = 8, max = 30, message = "La password deve essere compresa tra gli 8 e i 30 caratteri")
    String password,

    @NotBlank(message = "Il ruolo deve essere inserito")
    @Size(min = 6, max = 15, message = "Il ruolo deve essere compreso tra i 6 e i 15 caratteri")
    String ruolo
) {}
