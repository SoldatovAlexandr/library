package edu.asoldatov.library.erroritem.code;

public enum ServerErrorCodeWithField {
    NO_PERMISSION("no permission", "bookId"),

    BOOK_TAKEN("book taken", "bookId"),

    PASSWORDS_NOT_EQUALS("wrong password", "password"),

    EMPTY_DATABASE("database is empty", "userId"),

    WRONG_USERNAME("wrong username", "username"),

    WRONG_AUTHOR_ID("wrong author id", "authorId"),

    WRONG_GENRE_ID("wrong genre id", "genreId"),

    WRONG_BOOK_ID("wrong book id", "bookId"),

    WRONG_USER_ID("wrong user id", "userId");

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
