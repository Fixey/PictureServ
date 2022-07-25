package ru.liga.pictureserv.sending;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

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


    public byte[] getImageBase64() {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File("temp/image.jpg"));
//            return Base64.getEncoder().encodeToString(fileContent);
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
