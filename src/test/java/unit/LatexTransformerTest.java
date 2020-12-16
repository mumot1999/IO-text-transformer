package unit;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.logic.base.LatexTransformer;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LatexTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("%&$", "\\%\\&\\$"),
                arguments("{}_", "\\{\\}\\_"),
                arguments("^~\\", "\\textasciicircum\\textasciitilde\\textbackslash"),
                arguments("", ""),
                arguments("brak znakow specjalnych" , "brak znakow specjalnych"),
                arguments("\n\t\s", "\n\t\s"),
                arguments("\\n\\t\\s", "\\textbackslashn\\textbackslasht\\textbackslashs"),
                arguments("\\\\", "\\textbackslash\\textbackslash")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new LatexTransformer(new Text(in)).getText();

        Assertions.assertEquals(transformed_text, out);
    }
}