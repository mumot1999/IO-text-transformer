package pl.put.poznan.transformer.logic;

public class LowerCaseTransformer extends TextDecorator {
    public LowerCaseTransformer(Text text) {
        super(text);
    }

    @Override
    public String getText() {
        return super.getText().toLowerCase();
    }
}
