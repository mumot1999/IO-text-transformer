package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

/**
 * UpperCaseTransformer is a subclass (concrete decorator) which takes a text string and makes its all letters upper case.
 * It's a part of the case-transformation kit along with LowerCaseTransformer and CapitalizeTransformer.
 * It extends TextDecorator.
 * @see LowerCaseTransformer
 * @see CapitalizeTransformer
 * @see TextDecorator
 */
public class UpperCaseTransformer extends TextDecorator {

    /**
     * Constructor of UpperCaseTransformer.
     * @param text  The text whose letters will be made upper case.
     */
    public UpperCaseTransformer(TextTransformerInterface text) {
        super(text);
    }

    /**
     * Main method of the class UpperCaseTransformer.
     * @return String in which all letters are upper case.
     */
    @Override
    public String getText() {
        return super.getText().toUpperCase();
    }
}
