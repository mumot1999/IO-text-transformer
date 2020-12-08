package pl.put.poznan.transformer.logic.base;

import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

public class Text implements TextTransformerInterface {
    private final String text;

    public Text(String text){
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
