package ru.liga.pictureserv.painting;

import org.springframework.stereotype.Service;

import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CreatingTextLayout {
    /**
     * Сформировать слои для строк
     *
     * @param mapText               текст (заголовок, тело)
     * @param lineBreakMeasurerList список стилей налоеженных на текст
     * @param breakWidth            шинира картинки
     * @return List<TextLayout> список слоев
     */
    public List<TextLayout> getListTextLayout(Map<String, String> mapText,
                                              List<LineBreakMeasurer> lineBreakMeasurerList,
                                              float breakWidth) {
        List<TextLayout> textLayoutList = new LinkedList<>();
        List<String> listText = new LinkedList<>(mapText.values());
        List<String> listTextSorted = listText.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        for (int i = 0; i < lineBreakMeasurerList.size(); i++) {
            LineBreakMeasurer line = lineBreakMeasurerList.get(i);
            while (line.getPosition() < listTextSorted.get(i).length()) {
                TextLayout textLayout = line.nextLayout(breakWidth);
                textLayoutList.add(textLayout);
                if (textLayoutList.size() > 50) {
                    break;
                }
            }
        }
        return textLayoutList;
    }
}
