package unit;

import org.apache.commons.collections.bag.TransformedSortedBag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformerService;
import pl.put.poznan.transformer.logic.TransformerProvider;

import java.lang.reflect.InvocationTargetException;

public class TextTransformerServiceTest {
    @Test
    public void test_should_transform_text() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TextTransformerService textTransformerService = new TextTransformerService(new TransformerProvider());
        String[] t = {"capitalize", "lower"};
        Assertions.assertEquals("asd", textTransformerService.transformText("asd", t));
    }
}
