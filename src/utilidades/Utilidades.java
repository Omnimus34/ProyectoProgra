package utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Utilidades {
    // Valida si un string es un correo simple
    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    // Valida si un string es un número de teléfono de 8 dígitos
    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("^\\d{8}$");
    }

    // Intenta parsear una fecha, retorna null si es inválida
    public static LocalDate parseFecha(String fecha) {
        try {
            return LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
