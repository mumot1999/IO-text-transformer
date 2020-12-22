package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RepetitionEliminationTransformer is a subclass which takes a text string and returns a string with neighbouring repeated words eliminated.
 * It works for any number of neighbouring repeating words.
 * Supports UTF-8 encoding.
 * It extends:
 * @see TextDecorator
 */
public class RepetitionEliminationTransformer extends TextDecorator {
    /**
     * Constructor of RepetitionEliminationTransformer
     * @param text  The text in which neighbouring repeated words will be eliminated.
     */
    public RepetitionEliminationTransformer(TextTransformerInterface text) {
        super(text);
    }

    /**
     * Main method of the class RepetitionEliminationTransformer.
     * @return String with neighbouring repeated words eliminated.
     */
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
