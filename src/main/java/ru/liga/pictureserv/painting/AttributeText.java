package ru.liga.pictureserv.painting;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

@Service
public class AttributeText {
    public AttributedString getAttributedString(String text, Font mainFont) {
        AttributedString attributedText = new AttributedString(text);
        attributedText.addAttribute(TextAttribute.FONT, mainFont);
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.BLACK);
        return attributedText;
    }
}
