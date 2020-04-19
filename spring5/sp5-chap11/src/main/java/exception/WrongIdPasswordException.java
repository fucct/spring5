package exception;

public class WrongIdPasswordException extends RuntimeException {
    public WrongIdPasswordException() {
    }

    public WrongIdPasswordException(final String message) {
        super(message);
    }
}
