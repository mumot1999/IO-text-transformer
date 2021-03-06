package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.LatexTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LatexTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("%&$", "\\%\\&\\$"),
                arguments("{}_", "\\{\\}\\_"),
                arguments("^~\\", "\\textasciicircum\\textasciitilde\\textbackslash"),
                arguments("brak znakow specjalnych" , "brak znakow specjalnych"),
                arguments("\n\t", "\n\t"),
                arguments("\\n\\t\\s", "\\textbackslashn\\textbackslasht\\textbackslashs"),
                arguments("\\\\", "\\textbackslash\\textbackslash")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new LatexTransformer(new Text(in)).getText();
        Assertions.assertEquals(out, transformed_text);
    }

    @Test
    void latexTransformerAcceptanceCriterion(){
        Assertions.assertEquals("\\&", new LatexTransformer(new Text("&")).getText());
        Assertions.assertEquals("\\$", new LatexTransformer(new Text("$")).getText());
    }

    @Test
    void latexTransformerEmptyTest(){
        Assertions.assertEquals("", new LatexTransformer(new Text("")).getText());
    }
}