package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

public class UpperCaseTransformer extends TextDecorator {
    public UpperCaseTransformer(TextTransformerInterface text) {
        super(text);
    }

    @Override
    public String getText() {
        return super.getText().toUpperCase();
    }
}
