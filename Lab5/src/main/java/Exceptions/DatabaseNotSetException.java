package Exceptions;

public class DatabaseNotSetException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DatabaseNotSetException(String message) {
        super(message);
    }
}
