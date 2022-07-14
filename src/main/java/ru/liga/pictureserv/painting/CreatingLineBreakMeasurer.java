package ru.liga.pictureserv.painting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedString;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CreatingLineBreakMeasurer {
    final private AttributeText attributeText;

    @Autowired
    public CreatingLineBreakMeasurer(AttributeText attributeText) {
        this.attributeText = attributeText;
    }

    /**
     * Сфофрмировать список стилей налоеженных на текст
     *
     * @param mapText   текст (заголовок, тело)
     * @param mainFont  стиль для тела
     * @param mainFontB сталь для заголовка
     * @param frc       класс для корректного изображения
     * @return List<LineBreakMeasurer> писок стилей налоеженных на текст
     */
    public List<LineBreakMeasurer> makeListLineBreakMeasurer(Map<String, String> mapText,
                                                             Font mainFont,
                                                             Font mainFontB,
                                                             FontRenderContext frc) {
        AttributedString attributedHead = attributeText.getAttributedString(mapText.get("header"), mainFontB);
        AttributedString attributedBody = attributeText.getAttributedString(mapText.get("body"), mainFont);
        LineBreakMeasurer lineMeasurerHeader = new LineBreakMeasurer(attributedHead.getIterator(), frc);
        LineBreakMeasurer lineMeasurerBody = new LineBreakMeasurer(attributedBody.getIterator(), frc);
        List<LineBreakMeasurer> lineBreakMeasurersList = new LinkedList();
        lineBreakMeasurersList.add(lineMeasurerHeader);
        lineBreakMeasurersList.add(lineMeasurerBody);
        return lineBreakMeasurersList;
    }
}
