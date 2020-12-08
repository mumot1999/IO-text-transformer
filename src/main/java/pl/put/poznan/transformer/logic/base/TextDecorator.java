package pl.put.poznan.transformer.logic.base;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public abstract class TextDecorator implements TextTransformerInterface{

    private final TextTransformerInterface text;

    public TextDecorator(TextTransformerInterface text){
        this.text = text;
    }

    public String getText(){
        return text.getText();
    }

}
