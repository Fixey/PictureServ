package ru.liga.pictureserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.pictureserv.painting.CreatePicture;
import ru.liga.pictureserv.sending.SendingPicture;

import java.net.URL;


@RestController
public class ControllerWeb {
    final private SendingPicture sendingPicture;
    final private CreatePicture createPicture;

    @Autowired
    public ControllerWeb(SendingPicture sendingPicture, CreatePicture createPicture) {
        this.sendingPicture = sendingPicture;
        this.createPicture = createPicture;
    }

    @PostMapping(value = "/pict")
    public URL sendUrl(@RequestBody TextEntity TextEntity) {
        createPicture.createPicture(TextEntity.getText());
        return sendingPicture.getMyUrl();
    }

    @GetMapping
    public String getUrl() {
        return "Чё надо, холоп?";
    }
}
