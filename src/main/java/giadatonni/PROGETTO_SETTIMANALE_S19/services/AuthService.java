package giadatonni.PROGETTO_SETTIMANALE_S19.services;

import giadatonni.PROGETTO_SETTIMANALE_S19.entities.Utente;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.BadRequestException;
import giadatonni.PROGETTO_SETTIMANALE_S19.exceptions.UnauthorizedException;
import giadatonni.PROGETTO_SETTIMANALE_S19.payloads.LoginDTO;
import giadatonni.PROGETTO_SETTIMANALE_S19.security.JWTTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTTools jwtTools;
    private final UtentiService utentiService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JWTTools jwtTools, UtentiService utentiService, PasswordEncoder passwordEncoder) {
        this.jwtTools = jwtTools;
        this.utentiService = utentiService;
        this.passwordEncoder = passwordEncoder;
    }

    public String verificaCredenzialiEGeneraToken(LoginDTO body){

        Utente utente = this.utentiService.findByEmail(body.email());

        if (passwordEncoder.matches(body.password(), utente.getPassword())){
            String accessToken = this.jwtTools.generateToken(utente);
            return accessToken;
        } else {
            throw new UnauthorizedException("Password non valida");
        }

    }
}
