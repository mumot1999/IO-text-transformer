package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
                arguments("0,01", "jedna setna"),
                arguments("-378,123", "minus trzysta siedemdziesiąt osiem i sto dwadzieścia trzy tysięcznych"),
                arguments("100,08 zł", "sto i osiem setnych zł")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new NumberToTextTransformer(new Text(in)).getText();

        Assertions.assertEquals(out, transformed_text);
    }

    @Test
    void numberToTextTransformerAcceptanceCriterion(){
        Assertions.assertEquals("minus jeden", new NumberToTextTransformer(new Text("-1")).getText());
        Assertions.assertEquals("zero", new NumberToTextTransformer(new Text("0")).getText());
        Assertions.assertEquals("jeden", new NumberToTextTransformer(new Text("1")).getText());
        Assertions.assertEquals("dwa i siedemdziesiąt sześć setnych", new NumberToTextTransformer(new Text("2,76")).getText());
        Assertions.assertEquals("trzy i czternaście setnych", new NumberToTextTransformer(new Text("3,14")).getText());
        Assertions.assertEquals("cztery ... dziewięćset dziewięćdziesiąt osiem", new NumberToTextTransformer(new Text("4 ... 998")).getText());
        Assertions.assertEquals("dziewięćset dziewięćdziesiąt dziewięć i dziewięćdziesiąt dziewięć setnych", new NumberToTextTransformer(new Text("999.99")).getText());
        Assertions.assertEquals("tysiąc", new NumberToTextTransformer(new Text("1000")).getText());
    }

    @Test
    void numberToTextTransformerEmptyTest(){
        Assertions.assertEquals("", new NumberToTextTransformer(new Text("")).getText());
    }
}
