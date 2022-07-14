package ru.liga.pictureserv.controller;

import ru.liga.pictureserv.painting.CreatePicture;
import ru.liga.pictureserv.sending.SendingPicture;

//@ShellComponent
public class ShellController {
    private final CreatePicture createPicture;
    private final SendingPicture sendingPicture;

    //        @Autowired
    public ShellController(CreatePicture createPicture, SendingPicture sendingPicture) {
        this.createPicture = createPicture;
        this.sendingPicture = sendingPicture;
    }

    //    @ShellMethod("Create pict")
    public void conv(String text) {
        createPicture.createPicture(text);

    }
}
