package ru.liga.pictureserv.sending;

import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class SendingPicture {
    /**
     * Хранимые файлы
     */
    private URL url;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * Хранимые текстовые сообщения
     */
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
