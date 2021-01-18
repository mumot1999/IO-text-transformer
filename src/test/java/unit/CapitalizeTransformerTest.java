package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.CapitalizeTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class CapitalizeTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("TEST", "Test"),
                arguments("word", "Word"),
                arguments("To be or not to be?!", "To Be Or Not To Be?!"),
                arguments("The Lion King is the best movie EVER!", "The Lion King Is The Best Movie Ever!")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new CapitalizeTransformer(new Text(in)).getText();
        Assertions.assertEquals(out, transformed_text);
    }

    @Test
    void capitalizeTransformerAcceptanceCriterion(){
        String transformed_text = new CapitalizeTransformer(new Text("capitalize text")).getText();
        Assertions.assertEquals("Capitalize Text", transformed_text);
    }

    @Test
    void capitalizeTransformerEmptyTest(){
        Assertions.assertEquals("", new CapitalizeTransformer(new Text("")).getText());
    }
}