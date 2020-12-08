package pl.put.poznan.transformer.logic;

public class UpperCaseTransformer extends TextDecorator {
    public UpperCaseTransformer(String text) {
        super(text);
        transform();
    }

    private void transform(){
        this.text = this.text.toUpperCase();
    }
}
