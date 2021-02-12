package edu.asoldatov.library.erroritem.code;

public enum ServerErrorCode {
    //TODO
    BOOK_ID_NOT_SET("bookId must be set"),
    BIOGRAPHY_NOT_SET("biography must be set"),
    YEAR_OF_BIRTH_NOT_SET("year of birth must be set"),
    PATRONYMIC_NOT_SET("patronymic must be set"),
    GENRE_ID_NOT_SET("genre id must be set"),
    NAME_NOT_SET("name must be set"),
    YEAR_OF_PUBLISHING_NOT_SET("year of publishing must be set"),
    PARAMS_NOT_SET("params must be set"),
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
