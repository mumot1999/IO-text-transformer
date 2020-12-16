package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.AcronymTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AcronymTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("prof. Maciej", "profesor Maciej"),
                arguments("pRof. Maciej", "profesor Maciej"),
                arguments("Prof. Maciej", "Profesor Maciej"),
                arguments("PROF. Maciej", "Profesor Maciej"),
                arguments("np. chleb", "na przykład chleb"),
                arguments("NP. chleb", "Na Przykład chleb"),
                arguments("nP. chleb", "na Przykład chleb"),
                arguments("dr. Maciej", "doktor Maciej"),
                arguments("dR. Maciej", "doktor Maciej"),
                arguments("DR. Maciej", "Doktor Maciej"),
                arguments("chleb itp.", "chleb i tym podobne"),
                arguments("chleb Itp.", "chleb I tym podobne"),
                arguments("chleb iTp.", "chleb i Tym podobne"),
                arguments("chleb itP.", "chleb i tym Podobne"),
                arguments("chleb ITP.", "chleb I Tym Podobne"),
                arguments("chleb itd.", "chleb i tak dalej"),
                arguments("chleb Itd.", "chleb I tak dalej"),
                arguments("chleb iTd.", "chleb i Tak dalej"),
                arguments("chleb itD.", "chleb i tak Dalej"),
                arguments("chleb ITD.", "chleb I Tak Dalej")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new AcronymTransformer(new Text(in)).getText();

        Assertions.assertEquals(out, transformed_text);
    }
}