package unit;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import static org.assertj.core.api.Assertions.assertThat;

class LowerCaseTransformerTest {
    @Test
    void should_lowercase_text(){
        TextTransformerInterface upperCaseTransformer = new LowerCaseTransformer(new Text("TEsT"));
        assertThat(upperCaseTransformer.getText())
                .isEqualTo("test");
    }
}