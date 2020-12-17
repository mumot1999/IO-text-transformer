package pl.put.poznan.transformer.logic;

import org.yaml.snakeyaml.util.ArrayUtils;
import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

class TextToAcronymTranslation {
    public String firstWord;
    public String secondWord;
    public String thirdWord;
    public String acronym;

    TextToAcronymTranslation(String firstWord,String secondWord,String thirdWord, String acronym) {
        this.acronym=acronym;
        this.firstWord=firstWord;
        this.secondWord=secondWord;
        this.thirdWord=thirdWord;
    }
}

public class TextToAcronymTransformer extends TextDecorator {
    public TextToAcronymTransformer(TextTransformerInterface text) {
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
        TextToAcronymTranslation[] translationsArray = new TextToAcronymTranslation[4];
        translationsArray[0] = new TextToAcronymTranslation("na", "przykład","","np.");
        translationsArray[1] = new TextToAcronymTranslation("między", "innymi","","m.in.");
        translationsArray[2] = new TextToAcronymTranslation("i", "tym","podobne","itp.");
        translationsArray[3] = new TextToAcronymTranslation("i", "tak","dalej","itd.");
        String textSplit[] = text.split("\\s+");
        List<String> words = new ArrayList<String>(Arrays.asList(textSplit));
        for (int j = 0; j < words.size(); j++) {
            for (int i=0; i<translationsArray.length;i++){
                if(words.get(j).toLowerCase().equals(translationsArray[i].firstWord)){
                    if(words.get(j+1).toLowerCase().equals(translationsArray[i].secondWord)){
                        if(!translationsArray[i].thirdWord.equals("") && words.get(j+2).toLowerCase().equals(translationsArray[i].thirdWord)){
                            words.remove(j+2);
                            words.remove(j+1);
                            words.set(j, translationsArray[i].acronym);
                        } else {
                            words.remove(j+1);
                            words.set(j, translationsArray[i].acronym);
                        }
                    }
                }

            }
        }

        return String.join(" ", words);
    }

}
