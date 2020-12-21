package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextTransformerService {
    private static final Map<String, Class> transformNames = null;
    public TextTransformerService(){
//        transformNames.put("upper", UpperCaseTransformer.class);
    }

    public String transformText(String text, String[] transformers){
        TextTransformerInterface _text = new Text(text);

        for (String transformer: transformers) {
            _text = new UpperCaseTransformer(_text);
        }

        return _text.getText();
    }
}

