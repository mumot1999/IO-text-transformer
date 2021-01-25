package pl.put.poznan.transformer.logic;
import pl.put.poznan.transformer.logic.base.TextDecorator;

import java.util.HashMap;
import java.util.Map;

public class TransformerProvider {

    private static HashMap<String, Class> transformNames;
    public TransformerProvider(){
        transformNames = new HashMap<>();
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
    public Class getTransformer(String transformerName) throws NoSuchMethodException, NullPointerException{

        for (String t : transformNames.keySet()){
            if(transformerName.equals(t)){
                return transformNames.get(t);
            }
        }
        return null;
    }

}
