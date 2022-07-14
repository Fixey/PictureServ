package ru.liga.pictureserv.parsetext.exception;

public class CantParseTextException extends RuntimeException {
    public CantParseTextException() {
        super("Can't parse text!");
    }
}

