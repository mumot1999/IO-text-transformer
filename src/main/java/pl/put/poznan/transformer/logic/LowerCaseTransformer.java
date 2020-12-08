package pl.put.poznan.transformer.logic;

public class LowerCaseTransformer extends TextDecorator {
    public LowerCaseTransformer(String text) {
        super(text);
        transform();
    }

    private void transform(){
        this.text = this.text.toLowerCase();
    }
}
