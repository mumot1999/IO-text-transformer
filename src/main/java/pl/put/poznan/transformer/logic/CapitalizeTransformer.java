package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.Arrays;

/**
 * CapitalizeTransformer is a subclass (concrete decorator) which takes a text string and makes the first letter in each word upper case and the rest lower case.
 * It's a part of the case-transformation kit along with UpperCaseTransformer and LowerCaseTransformer.
 * It extends TextDecorator.
 * @see UpperCaseTransformer
 * @see LowerCaseTransformer
 * @see TextDecorator
 */
public class CapitalizeTransformer extends TextDecorator {

    /**
     * Constructor of CapitalizeTransformer.
     * @param text The text in which the first letter in each word will be made upper case and the rest lower case.
     */
    public CapitalizeTransformer(TextTransformerInterface text) {
        super(text);
    }

    /**
     * Main method of the class CapitalizeTransformer.
     * @return String in which the first letter in each word is upper case and the rest lower case.
     */
    @Override
    public String getText() {
        return String.join(" ",
                Arrays.stream(super.getText().toLowerCase().split(" ")).map(this::firstLetterToUpperCase).toArray(String[]::new)
        );
    }

    private String firstLetterToUpperCase(String text){
        if(text == ""){
            return "";
        }
        else{
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        }
    }
}
