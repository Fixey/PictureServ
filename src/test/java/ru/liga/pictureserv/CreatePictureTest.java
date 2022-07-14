package ru.liga.pictureserv;

import org.junit.jupiter.api.Test;
import ru.liga.pictureserv.painting.*;
import ru.liga.pictureserv.parsetext.ParseText;

class CreatePictureTest {

    @Test
    void createPicture() {
        String text = "Many people believe that Vincent van Gogh painted his best works " +
                "during the two-year period he spent in Provence. Here is where he " +
                "painted The Starry Night--which some consider to be his greatest " +
                "work of all. However, as his artistic brilliance reached new " +
                "heights in Provence, his physical and mental health plummeted."+
                "heights in Provence, his physical and mental health plummeted.";
        AttributeText attributeText = new AttributeText();
        new CreatePicture(new CreatingFont(), new ParseText(), attributeText, new CreatingTextLayout(), new CreatingLineBreakMeasurer(attributeText)).createPicture(text);
    }
}