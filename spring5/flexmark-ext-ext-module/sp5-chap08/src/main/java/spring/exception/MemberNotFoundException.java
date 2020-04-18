package spring.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
    }

    public MemberNotFoundException(final String message) {
        super(message);
    }
}
