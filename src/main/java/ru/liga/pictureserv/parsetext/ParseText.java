package ru.liga.pictureserv.parsetext;

import org.springframework.stereotype.Service;
import ru.liga.pictureserv.painting.exception.TextIsNotCorrectException;
import ru.liga.pictureserv.parsetext.exception.CantParseTextException;

import java.util.Map;

@Service
public class ParseText {
    /**
     * Распарсить текст
     *
     * @param text текст
     * @return Map<String, String> распарсинный текст
     * @throws TextIsNotCorrectException когда текст короткий
     * @throws CantParseTextException    когда не может распарсить
     */
    public Map<String, String> getMapFromText(String text) {
        try {
            String header, body;
            if (text.contains("\n")) {
                header = text.split("\n")[0];
            } else {
                header = text.split(" ")[0];
            }
            body = text.substring(header.length()).trim();
            if (body == null) {
                throw new TextIsNotCorrectException();
            }
            return Map.of("header", header, "body", body);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new CantParseTextException();
        }
    }
}
