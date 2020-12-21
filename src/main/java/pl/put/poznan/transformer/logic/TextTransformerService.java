package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TextTransformerService {
    private Map<String, Class> transformNames = new HashMap<>();
    public TextTransformerService(){
        transformNames.put("acronym", AcronymTransformer.class);
        transformNames.put("capitalize_inversion", CapitalizedInversionTransformer.class);
        transformNames.put("capitalize", CapitalizeTransformer.class);
        transformNames.put("latex", LatexTransformer.class);
        transformNames.put("lower", LowerCaseTransformer.class);
        transformNames.put("number_to_text", NumberToTextTransformer.class);
        transformNames.put("repetition_elimination", RepetitionEliminationTransformer.class);
        transformNames.put("text_to_acronym", TextToAcronymTransformer.class);
        transformNames.put("upper", UpperCaseTransformer.class);
    }

    public String transformText(String text, String[] transformers) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        TextTransformerInterface _text = new Text(text);

        for (String transformer: transformers) {
            _text = (TextTransformerInterface) this.transformNames.get(transformer).getDeclaredConstructors()[0].newInstance(_text);
        }

        return _text.getText();
    }
}

