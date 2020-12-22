package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.transformer.logic.CapitalizedInversionTransformer;
import pl.put.poznan.transformer.logic.RepetitionEliminationTransformer;
import pl.put.poznan.transformer.logic.base.Text;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RepetitionEliminationTransformerTest {

    static Stream<Arguments> transformsArgumentsProvider() {
        return Stream.of(
                arguments("Wyślij do do mnie wiadomość", "Wyślij do mnie wiadomość"),
                arguments("do do do", "do"),
                arguments("do do", "do"),
                arguments("do do po po", "do po"),
                arguments("Du du du duuum!", "Du du duuum!"),
                arguments("Cyberpunk Cyberpunk Cyberpunk Cyberpunk 2077", "Cyberpunk 2077"),
                arguments("❄︎⍓︎◻︎♏︎ ⍓︎□︎◆︎❒︎ ⧫︎♏︎⌧︎⧫︎ ⧫︎♏︎⌧︎⧫︎ ⧫︎♏︎⌧︎⧫︎ ⧫︎♏︎⌧︎⧫︎ ♒︎♏︎❒︎♏︎", "❄︎⍓︎◻︎♏︎ ⍓︎□︎◆︎❒︎ ⧫︎♏︎⌧︎⧫︎ ♒︎♏︎❒︎♏︎"),
                arguments("≻≼≽≾ ≻≼≽≾ ≻≼≽≾ ≻≼≽≾ ≿⊁ ≿⊁ ⊂⊃ ⊄⊅ ⊄⊅", "≻≼≽≾ ≿⊁ ⊂⊃ ⊄⊅"),
                arguments("asd\nasd", "asd")
        );
    }

    @ParameterizedTest(name = "should transform {0} into {1}")
    @MethodSource("transformsArgumentsProvider")
    void shouldTransform(String in, String out) {
        String transformed_text = new RepetitionEliminationTransformer(new Text(in)).getText();

        Assertions.assertEquals(transformed_text, out);
    }
}
