package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public abstract class TextDecorator implements TextTransformerInterface{

    protected String text;

    public TextDecorator(String text){
        this.text = text;
    }

    public TextDecorator(TextDecorator text_decorator){
        this.text = text_decorator.text;
    }

    public String getText(){
        return text;
    }

}
