package giadatonni.PROGETTO_SETTIMANALE_S19.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

    List<String> errorsList;

    public ValidationException(List<String> errorsList) {
        super("Errore nella validazione");
        this.errorsList = errorsList;
    }

    public List<String> getErrorsList() {
        return errorsList;
    }
}
