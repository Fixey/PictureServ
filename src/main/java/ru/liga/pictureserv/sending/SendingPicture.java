package ru.liga.pictureserv.sending;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Service


public class SendingPicture {
    /**
     * Хранимые файлы
     */
    @Getter
    private URL url;

    /**
     * Хранимые текстовые сообщения
     */
    @SneakyThrows
    public SendingPicture() {
        this.url = null;
    }


    public URL getMyUrl() {

        try {
            return new File("temp/image.jpg").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
