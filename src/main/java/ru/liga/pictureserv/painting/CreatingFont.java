package ru.liga.pictureserv.painting;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.liga.pictureserv.constant.ConstantUtil.OLDSTANDART_BOLD_PATH;
import static ru.liga.pictureserv.constant.ConstantUtil.OLDSTANDART_REG_PATH;

@Service
public class CreatingFont {
    @Getter
    private Font mainFontB;
    @Getter
    private Font mainFont;

    @SneakyThrows
    public CreatingFont() {
        Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(OLDSTANDART_BOLD_PATH));
        this.mainFontB = new Font("Old Standard TT", Font.BOLD, 50);
        Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(OLDSTANDART_REG_PATH));
        this.mainFont = new Font("Old Standard TT", Font.PLAIN, 45);
    }

    public Font getFont(BufferedImage image, Font f, double expectedHeight) {
        final boolean textFits = image.getHeight() < expectedHeight;
        Font newFont = f;
        if (textFits) {
            final double heightBasedFontSize = (f.getSize2D() * image.getHeight()) / expectedHeight;
            newFont = f.deriveFont(f.getStyle(), (float) heightBasedFontSize);
        }
        return newFont;
    }
}
