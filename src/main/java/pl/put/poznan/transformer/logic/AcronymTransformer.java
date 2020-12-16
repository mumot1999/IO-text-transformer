package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.Arrays;
import java.util.Hashtable;

class Translation {
    public String acronym;
    public String translation;

    Translation(String acronym, String translation) {
        this.acronym = acronym;
        this.translation = translation;
    }
}

public class AcronymTransformer extends TextDecorator {
    public AcronymTransformer(TextTransformerInterface text) {
        super(text);
    }

    @Override
    public String getText() {
        return String.join(" ",
                Arrays.stream(super.getText().split("\\s+")).map(this::transformWord).toArray(String[]::new)
        );
    }

    private String transformWord(String word) {
        try {
            return translate(word);
        } catch (NumberFormatException nfe) {
            return word;
        }
    }

    private String translate(String word) {
        Translation[] translationsArray = new Translation[5];
        translationsArray[0] = new Translation("prof.", "profesor");
        translationsArray[1] = new Translation("dr.", "doktor");
        translationsArray[2] = new Translation("np.", "na przykład");
        translationsArray[3] = new Translation("itp.", "i tym podobne");
        translationsArray[4] = new Translation("itd.", "i tak dalej");
        boolean ch1;
        boolean allCap=true;
        for (int j = 0; j < translationsArray.length; j++) {
            if (word.toLowerCase().equals(translationsArray[j].acronym)) {
                String[] words = translationsArray[j].translation.split("\\s+");
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    ch1 = Character.isUpperCase(c);
                    if (ch1 == true && i < words.length) {
                        words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
                        translationsArray[j].translation=String.join(" ", words);
                    }
                }
                word=translationsArray[j].translation;
            }
        }

        return word;
    }

}
