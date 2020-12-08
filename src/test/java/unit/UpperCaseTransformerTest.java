package unit;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.LowerCaseTransformer;
import pl.put.poznan.transformer.logic.TextDecorator;
import pl.put.poznan.transformer.logic.TextTransformerInterface;
import pl.put.poznan.transformer.logic.UpperCaseTransformer;

import static org.assertj.core.api.Assertions.assertThat;

class UpperCaseTransformerTest {
    @Test
    void should_uppercase_text(){
        UpperCaseTransformer upperCaseTransformer = new UpperCaseTransformer("test");
        assertThat(upperCaseTransformer.getText())
                .isEqualTo("TEST");
    }

    @Test
    void should_transform_using_many_transformers(){
        TextDecorator textTransformer = new UpperCaseTransformer(new LowerCaseTransformer("TeSt").getText());
        assertThat(textTransformer.getText())
                .isEqualTo("TEST");
    }
}