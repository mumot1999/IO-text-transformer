package unit;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.logic.base.Text;

import static org.assertj.core.api.Assertions.assertThat;

class UpperCaseTransformerTest {
    @Test
    void should_uppercase_text(){
        UpperCaseTransformer upperCaseTransformer = new UpperCaseTransformer(new Text("test"));
        assertThat(upperCaseTransformer.getText())
                .isEqualTo("TEST");
    }
}