package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.NumberToTextTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NumberToTextTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("mam 100 lat", "mam sto lat"),
                arguments("571", "pięćset siedemdziesiąt jeden"),
                arguments("825", "osiemset dwadzieścia pięć"),
                arguments("10,25", "dziesięć i dwadzieścia pięć setnych"),
                arguments("0,1", "jedna dziesiąta"),
                arguments("0,2", "dwie dziesiąte"),
                arguments("0,3", "trzy dziesiąte"),
                arguments("0,4", "cztery dziesiąte"),
                arguments("0,5", "pięć dziesiątych"),
                arguments("0,01", "jedna setna")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new NumberToTextTransformer(new Text(in)).getText();

        Assertions.assertEquals(out, transformed_text);
    }
}
