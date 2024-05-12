package co.edu.unbosque.livingcorp.exception;

public class PasswordNotEncryptedException extends Exception{
    public PasswordNotEncryptedException(String mensaje) {
        super(mensaje);
    }
}
