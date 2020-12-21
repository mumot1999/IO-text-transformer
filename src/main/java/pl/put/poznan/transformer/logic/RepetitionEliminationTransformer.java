package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepetitionEliminationTransformer extends TextDecorator {

    public RepetitionEliminationTransformer(TextTransformerInterface text) {
        super(text);
    }

    @Override
    public String getText() {
        return transformText(super.getText());
    }

    private String transformText(String text) {
        try {
            return translate(text);
        } catch (NumberFormatException nfe) {
            return text;
        }
    }

    private String translate(String text) {
        String textSplit[] = text.split("\\s+");
        List<String> words = new ArrayList<String>(Arrays.asList(textSplit));
        Boolean no_repetition = false;
        String result = "";

        while (no_repetition == false){
            result = words.get(0);
            no_repetition = true;
            for (int i = 1; i < words.size(); i++) {
                if (!words.get(i).equals(words.get(i-1))) {
                    result += " " + words.get(i);
                }
                else{
                    words.remove(i);
                    i++;
                    no_repetition = false;
                }
            }
        }
        return result;
    }
}
