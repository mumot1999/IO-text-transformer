package unit;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.CapitalizeTransformer;
import pl.put.poznan.transformer.logic.UpperCaseTransformer;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import static org.assertj.core.api.Assertions.assertThat;

class CapitalizeTransformerTest {
    @Test
    void should_capitalize_text(){
        TextTransformerInterface transformer = new CapitalizeTransformer(new Text("test capitalize"));
        assertThat(transformer.getText())
                .isEqualTo("Test Capitalize");
    }
}