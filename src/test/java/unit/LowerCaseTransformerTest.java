package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.LowerCaseTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class LowerCaseTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("TEST", "test"),
                arguments("Word", "word"),
                arguments("To be or not to be?!", "to be or not to be?!"),
                arguments("The Lion King is the Best Movie EVER!", "the lion king is the best movie ever!")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new LowerCaseTransformer(new Text(in)).getText();
        Assertions.assertEquals(out, transformed_text);
    }

    @Test
    void lowerCaseTransformerAcceptanceCriterion(){
        String transformed_text = new LowerCaseTransformer(new Text("Lower")).getText();
        Assertions.assertEquals("lower", transformed_text);
    }

    @Test
    void lowerCaseTransformerEmptyTest(){
        Assertions.assertEquals("", new LowerCaseTransformer(new Text("")).getText());
    }
}