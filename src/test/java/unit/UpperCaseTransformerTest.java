package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.UpperCaseTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class UpperCaseTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("test", "TEST"),
                arguments("Word", "WORD"),
                arguments("To be or not to be?!", "TO BE OR NOT TO BE?!"),
                arguments("AOSJAJSHDLKAJBDLAJDSNKJ", "AOSJAJSHDLKAJBDLAJDSNKJ")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new UpperCaseTransformer(new Text(in)).getText();
        Assertions.assertEquals(out, transformed_text);
    }

    @Test
    void upperCaseTransformerAcceptanceCriterion(){
        String transformed_text = new UpperCaseTransformer(new Text("upper")).getText();
        Assertions.assertEquals("UPPER", transformed_text);
    }

    void upperCaseTransformerEmptyTest(){
        Assertions.assertEquals("", new UpperCaseTransformer(new Text("")).getText());
    }
}