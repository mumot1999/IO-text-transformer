package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.base.TextTransformerInterface;
import pl.put.poznan.transformer.logic.base.TextDecorator;

import javax.print.DocFlavor;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LatexTransformer extends TextDecorator{
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
