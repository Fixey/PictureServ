package ru.liga.pictureserv.painting.exception;

public class TextIsNotCorrectException extends RuntimeException {
    public TextIsNotCorrectException() {
        super("Input is not correct!");
    }
}