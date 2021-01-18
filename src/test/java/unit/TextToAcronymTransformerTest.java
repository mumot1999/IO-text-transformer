package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.TextToAcronymTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
class TextToAcronymTransformerTest {
    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("na przykład chleb", "np. chleb"),
                arguments("Na Przykład chleb", "np. chleb"),
                arguments("na Przykład chleb", "np. chleb"),
                arguments("chleb i tym podobne", "chleb itp."),
                arguments("chleb I TYM podobne", "chleb itp."),
                arguments("chleb i tym Podobne", "chleb itp."),
                arguments("chleb i tak dalej", "chleb itd."),
                arguments("chleb I TAK DALEJ", "chleb itd."),
                arguments("chleb i tak Dalej", "chleb itd."),
                arguments("chleb między innymi", "chleb m.in."),
                arguments("chleb między innymi", "chleb m.in."),
                arguments("chleb między innymi", "chleb m.in."),
                arguments("Przykład nad przykłady!", "Przykład nad przykłady!"),
                arguments("Na przykładowym stole leży obrus.", "Na przykładowym stole leży obrus.")

        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new TextToAcronymTransformer(new Text(in)).getText();
        Assertions.assertEquals(out, transformed_text);
    }

    @Test
    void textToAcronymTransformerAcceptanceCriterion(){
        Assertions.assertEquals("np.", new TextToAcronymTransformer(new Text("na przykład")).getText());
        Assertions.assertEquals("m.in.", new TextToAcronymTransformer(new Text("między innymi")).getText());
        Assertions.assertEquals("itp.", new TextToAcronymTransformer(new Text("i tym podobne")).getText());
    }

    void textToAcronymTransformerEmptyTest(){
        Assertions.assertEquals("", new TextToAcronymTransformer(new Text("")).getText());
    }
}