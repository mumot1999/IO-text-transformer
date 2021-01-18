package unit;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CapitalizedInversionTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("Ala ma Mruczka", "Akzcurm am ala"),
                arguments("AktaGenerałaMaMałaRenegatka", "AktaGenerałaMaMałaRenegatka"),
                arguments("abC", "cbA"),
                arguments("Dd", "Dd"),
                arguments("-1", "1-"),
                arguments("\n", "\n"),
                arguments("\n\t\t", "\t\t\n"),
                arguments(" D D", "d d ")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new CapitalizedInversionTransformer(new Text(in)).getText();
        Assertions.assertEquals(out, transformed_text);
    }

    @Test
    void capitalizedInversionTransformerAcceptanceCriterion(){
        String transformed_text = new CapitalizedInversionTransformer(new Text("MirEk")).getText();
        Assertions.assertEquals("KerIm", transformed_text);
    }

    @Test
    void capitalizedInversionTransformerEmptyTest(){
        Assertions.assertEquals("", new CapitalizedInversionTransformer(new Text("")).getText());
    }
}
