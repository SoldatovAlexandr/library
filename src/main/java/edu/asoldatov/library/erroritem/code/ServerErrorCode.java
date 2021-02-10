package edu.asoldatov.library.erroritem.code;

public enum ServerErrorCode {
    //TODO
    GENRE_ID_NOT_SET("GENRE_ID_NOT_SET"),
    NAME_NOT_SET("NAME_NOT_SET"),
    YEAR_OF_PUBLISHING_NOT_SET("YEAR_OF_PUBLISHING_NOT_SET"),
    PARAMS_NOT_SET("PARAMS_NOT_SET"),
    FIRST_NAME_NOT_SET("first name must be set"),
    LAST_NAME_NOT_SET("last name must be set"),
    LOGIN_NOT_SET("login must be set");

    private final String message;

    ServerErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
