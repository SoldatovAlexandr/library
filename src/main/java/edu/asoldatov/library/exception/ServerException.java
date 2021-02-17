package edu.asoldatov.library.exception;


public class ServerException extends Exception {
    private final String message;

    public ServerException(String message) {
        this.message = message;
    }

    public String getServerErrorCodeWithField() {
        return message;
    }
}
