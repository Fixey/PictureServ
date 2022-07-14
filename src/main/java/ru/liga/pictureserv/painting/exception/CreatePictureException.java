package ru.liga.pictureserv.painting.exception;

public class CreatePictureException extends RuntimeException {
    public CreatePictureException() {
        super("Can't create picture!");
    }
}
