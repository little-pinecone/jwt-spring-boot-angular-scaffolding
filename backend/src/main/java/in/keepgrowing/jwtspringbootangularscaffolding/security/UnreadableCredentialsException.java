package in.keepgrowing.jwtspringbootangularscaffolding.security;

public class UnreadableCredentialsException extends RuntimeException {

    public UnreadableCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
