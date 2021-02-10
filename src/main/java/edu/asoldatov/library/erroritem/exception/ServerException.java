package edu.asoldatov.library.erroritem.exception;

import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;

public class ServerException extends Exception {
    private final ServerErrorCodeWithField serverErrorCodeWithField;

    public ServerException(ServerErrorCodeWithField serverErrorCodeWithField) {
        this.serverErrorCodeWithField = serverErrorCodeWithField;
    }

    public ServerErrorCodeWithField getServerErrorCodeWithField() {
        return serverErrorCodeWithField;
    }
}
