package edu.asoldatov.library.erroritem.code;

public enum ServerErrorCodeWithField {
    //TODO
    WRONG_GENRE_ID("wrong genre id", "id"),
    WRONG_BOOK_ID("wrong book id", "id"),
    WRONG_ID("wrong id", "id");

    private final String message;
    private final String field;

    ServerErrorCodeWithField(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
