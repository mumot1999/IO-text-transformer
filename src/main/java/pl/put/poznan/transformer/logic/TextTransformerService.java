package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TextTransformerService {

    private TransformerProvider provider;

    public TextTransformerService(TransformerProvider provider){

        this.provider = provider;
    }

    public String transformText(String text, String[] transformers) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        TextTransformerInterface _text = new Text(text);

        for (String transformer: transformers) {
            _text = (TextTransformerInterface) provider.getTransformer(transformer).getDeclaredConstructors()[0].newInstance(_text);
        }

        return _text.getText();
    }
}

