package giadatonni.PROGETTO_SETTIMANALE_S19.controllers;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.ValidationException;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.UtenteDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.services.UtentiService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UtentiService utentiService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UtentiService utentiService, PasswordEncoder passwordEncoder) {
        this.utentiService = utentiService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente registraUtente(@RequestBody @Validated UtenteDTO body, BindingResult validationResults){
        if(validationResults.hasErrors()){
            List<String> errorsList = validationResults.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }
        return this.utentiService.saveUtente(body);
    }
}
