package ru.liga.pictureserv.controller;

public class TextEntity {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextEntity(String text) {
        this.text = text;
    }

    public TextEntity() {
    }
}
