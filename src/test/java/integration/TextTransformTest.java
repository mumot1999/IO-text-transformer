package integration;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.LowerCaseTransformer;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextDecorator;
import pl.put.poznan.transformer.logic.UpperCaseTransformer;

import static org.assertj.core.api.Assertions.assertThat;

public class TextTransformTest {
    @Test
    void should_transform_using_many_transformers(){
        TextDecorator textTransformer = new UpperCaseTransformer(
                new LowerCaseTransformer(new Text("TeSt"))
        );
        assertThat(textTransformer.getText())
                .isEqualTo("TEST");
    }
}
