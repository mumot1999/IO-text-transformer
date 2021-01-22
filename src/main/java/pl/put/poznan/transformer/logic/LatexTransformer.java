package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextTransformerInterface;
import pl.put.poznan.transformer.logic.base.TextDecorator;

import javax.print.DocFlavor;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LatexTransformer is a subclass (concrete decorator) which takes a text string and transforms it to a Latex-supported form in terms of special characters.
 * It supports characters like: \, &amp;, %, $, #, _, {, }, ~, ^.
 * It extends TextDecorator.
 * @see TextDecorator
 */
public class LatexTransformer extends TextDecorator{

    /**
     * Constructor of LatexTransformer.
     * @param text  The text that will be transformed to a Latex-supported form in terms of special characters.
     */
    public LatexTransformer(TextTransformerInterface text){ super(text);}

    private static final  Map<String, String> latexDictionary = Stream.of(new String[][]{
            {"\\&", "\\\\&"},
            {"\\%", "\\\\%"},
            {"\\$", "\\\\\\$"},
            {"\\#", "\\\\#"},
            {"\\_", "\\\\_"},
            {"\\{", "\\\\{"},
            {"\\}", "\\\\}"},
            {"\\~", "\\\\\\textasciitilde"},
            {"\\^", "\\\\\\textasciicircum"}
    }).collect(Collectors.collectingAndThen(
            Collectors.toMap(data->data[0], data->data[1]),
            Collections::<String, String> unmodifiableMap
    ));

    /**
     * Main method of the class LatexTransformer.
     * @return String in a Latex-supported form in terms of special characters.
     */
    @Override
    public String getText(){

        String copy = super.getText();
        Integer n = latexDictionary.size();

        //first replace backslashes
        copy = copy.replaceAll("\\\\", "\\\\\\textbackslash");

        //remaining characters
        for(Map.Entry s : latexDictionary.entrySet()){
            copy = copy.replaceAll(s.getKey().toString(), s.getValue().toString());
        }

        return copy;
    }
}
