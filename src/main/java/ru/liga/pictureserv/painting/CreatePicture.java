package ru.liga.pictureserv.painting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.pictureserv.painting.exception.CreatePictureException;
import ru.liga.pictureserv.parsetext.ParseText;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static ru.liga.pictureserv.constant.ConstantUtil.*;

@Service
public class CreatePicture {
    final private CreatingFont creatingFont;
    final private ParseText parseText;
    final private AttributeText attributeText;
    final private CreatingTextLayout creatingTextLayout;
    final private CreatingLineBreakMeasurer creatingLineBreakMeasurer;

    @Autowired
    public CreatePicture(CreatingFont creatingFont, ParseText parseText, AttributeText attributeText, CreatingTextLayout creatingTextLayout, CreatingLineBreakMeasurer creatingLineBreakMeasurer) {
        this.creatingFont = creatingFont;
        this.parseText = parseText;
        this.attributeText = attributeText;
        this.creatingTextLayout = creatingTextLayout;
        this.creatingLineBreakMeasurer = creatingLineBreakMeasurer;
    }

    /**
     * Создать картинку
     *
     * @param text текс
     */
    public void createPicture(String text) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BACKGROUND_PATH);
            BufferedImage image = ImageIO.read(inputStream);
            addTextToPicture(text, image);
            ImageIO.write(image, "jpg", new File(PATH_SAVING_PICTURE));
        } catch (IOException e) {
            throw new CreatePictureException();
        }
    }

    /**
     * Добавить текст на картинку
     *
     * @param text  текс
     * @param image картинка
     */
    private void addTextToPicture(String text, BufferedImage image) {
        Font mainFont = creatingFont.getMainFont();
        Font mainFontB = creatingFont.getMainFontB();
        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        FontRenderContext frc = g2d.getFontRenderContext();
        Map<String, String> mapText = parseText.getMapFromText(text);
        List<LineBreakMeasurer> lineBreakMeasurerList = creatingLineBreakMeasurer.makeListLineBreakMeasurer(mapText,
                creatingFont.getMainFont(),
                creatingFont.getMainFontB(),
                frc);
        final int breakWidth = image.getWidth() - PADDING;
        final int breakHeight = image.getHeight();
        List<TextLayout> linesText = creatingTextLayout.getListTextLayout(mapText, lineBreakMeasurerList, breakWidth);
        float heightText = getHeightText(linesText);
        Font finalFont = creatingFont.getFont(image, mainFont, heightText);
        Font finalFontB = mainFontB.deriveFont(mainFontB.getStyle(), mainFontB.getSize2D() + finalFont.getSize2D() - mainFont.getSize2D());
        lineBreakMeasurerList = creatingLineBreakMeasurer.makeListLineBreakMeasurer(mapText,
                finalFont,
                finalFontB,
                frc);
        linesText = creatingTextLayout.getListTextLayout(mapText, lineBreakMeasurerList, breakWidth);
        drawLines(g2d, breakHeight, linesText);
        g.dispose();
        g2d.dispose();
    }

    /**
     * Нарисовать линии
     *
     * @param g2d         картинка
     * @param breakHeight суммарная высота строк на картинке
     * @param linesText   линии текста
     */
    private void drawLines(Graphics2D g2d, int breakHeight, List<TextLayout> linesText) {
        final float heightText = getHeightText(linesText);
        float y = (breakHeight - heightText) / 2;
        for (TextLayout line : linesText) {
            float x = PADDING;
            line.draw(g2d, x, y + line.getAscent());
            y += line.getAscent() + line.getDescent() + line.getLeading();
        }
    }


    /**
     * Рассчитывает высоту отображаемого текста
     *
     * @param lines лист слоев строк
     * @return float высота отображаемого текста
     */
    private float getHeightText(List<TextLayout> lines) {
        float textHeight = 0;
        for (TextLayout line : lines) {
            textHeight += line.getAscent() + line.getDescent() + line.getLeading();
        }
        return textHeight;
    }
}
