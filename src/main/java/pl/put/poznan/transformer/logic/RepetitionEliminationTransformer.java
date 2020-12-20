package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

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
        return text;
    }
}
