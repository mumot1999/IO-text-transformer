package pl.put.poznan.transformer.logic;

public class UpperCaseTransformer extends TextDecorator {
    public UpperCaseTransformer(TextTransformerInterface text) {
        super(text);
    }

    @Override
    public String getText() {
        return super.getText().toUpperCase();
    }
}
