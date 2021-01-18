package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.util.Arrays;

public class CapitalizeTransformer extends TextDecorator {
    public CapitalizeTransformer(TextTransformerInterface text) {
        super(text);
    }

    @Override
    public String getText() {
        return String.join(" ",
                Arrays.stream(super.getText().toLowerCase().split(" ")).map(this::firstLetterToUpperCase).toArray(String[]::new)
        );
    }

    private String firstLetterToUpperCase(String text){
        if(text == ""){
            return "";
        }
        else{
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        }
    }
}
