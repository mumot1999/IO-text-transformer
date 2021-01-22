package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

/**
 * LowerCaseTransformer is a subclass (concrete decorator) which takes a text string and makes its all letters lower case.
 * It's a part of the case-transformation kit along with UpperCaseTransformer and CapitalizeTransformer.
 * It extends TextDecorator.
 * @see UpperCaseTransformer
 * @see CapitalizeTransformer
 * @see TextDecorator
 */
public class LowerCaseTransformer extends TextDecorator {

    /**
     * Constructor of LowerCaseTransformer.
     * @param text  The text whose letters will be made lower case.
     */
    public LowerCaseTransformer(TextTransformerInterface text) {
        super(text);
    }

    /**
     * Main method of the class LowerCaseTransformer.
     * @return String in which all letters are lower case.
     */
    @Override
    public String getText() {
        return super.getText().toLowerCase();
    }
}
