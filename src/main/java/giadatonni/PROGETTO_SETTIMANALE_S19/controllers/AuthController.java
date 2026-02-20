package giadatonni.PROGETTO_SETTIMANALE_S19.controllers;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.ValidationException;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.LoginDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.LoginResponseDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.UtenteDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.security.JWTTools;
import giadatonni.PROGETTO_SETTIMANALE_S19.services.AuthService;
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
    private final AuthService authService;

    public AuthController(UtentiService utentiService, AuthService authService) {
        this.utentiService = utentiService;
        this.authService = authService;
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

    @PostMapping("/login")
    public LoginResponseDTO registraUtente(@RequestBody @Validated LoginDTO body, BindingResult validationResults){
        if(validationResults.hasErrors()){
            List<String> errorsList = validationResults.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }
        String accessToken = this.authService.verificaCredenzialiEGeneraToken(body);
        return new LoginResponseDTO(accessToken);
    }
}
