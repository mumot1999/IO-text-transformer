package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.CapitalizeTransformer;
import pl.put.poznan.transformer.logic.UpperCaseTransformer;
import pl.put.poznan.transformer.logic.LowerCaseTransformer;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

public class MultipleTransformerTest {

    private Text text = new Text("Ale zaBAWA");

    @Test
    public void lower_upper_capitalize(){
        Text finalText = new Text(new UpperCaseTransformer(new Text(new CapitalizeTransformer(new Text(
                new LowerCaseTransformer(text).getText())).getText())).getText());
        Assertions.assertEquals("ALE ZABAWA", finalText.getText());
    }
}
