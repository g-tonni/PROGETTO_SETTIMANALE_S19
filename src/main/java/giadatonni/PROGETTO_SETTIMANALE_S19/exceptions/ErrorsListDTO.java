package giadatonni.PROGETTO_SETTIMANALE_S19.exceptions;

import java.util.List;

public record ErrorsListDTO (String error, List<String> errorsList) {
}
