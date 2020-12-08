package unit;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.*;

import static org.assertj.core.api.Assertions.assertThat;

class UpperCaseTransformerTest {
    @Test
    void should_uppercase_text(){
        UpperCaseTransformer upperCaseTransformer = new UpperCaseTransformer(new Text("test"));
        assertThat(upperCaseTransformer.getText())
                .isEqualTo("TEST");
    }

    @Test
    void should_transform_using_many_transformers(){
        TextDecorator textTransformer = new UpperCaseTransformer(
                new LowerCaseTransformer(new Text("TeSt"))
        );
        assertThat(textTransformer.getText())
                .isEqualTo("TEST");
    }
}