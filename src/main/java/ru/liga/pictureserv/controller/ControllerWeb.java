package ru.liga.pictureserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.pictureserv.painting.CreatePicture;
import ru.liga.pictureserv.sending.SendingPicture;


@RestController
public class ControllerWeb {
    final private SendingPicture sendingPicture;
    final private CreatePicture createPicture;

    @Autowired
    public ControllerWeb(SendingPicture sendingPicture, CreatePicture createPicture) {
        this.sendingPicture = sendingPicture;
        this.createPicture = createPicture;
    }

    @PostMapping(value = "/pict", produces = "jpg")
    public byte[] sendImage(@RequestBody TextEntity TextEntity) {
        createPicture.createPicture(TextEntity.getText());
        return sendingPicture.getImageBase64();
    }

    @GetMapping
    public String getUrl() {
        return "Чё надо, холоп?";
    }
}
