package exception;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(final String message) {
        super(message);
    }
}
