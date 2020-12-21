package unit;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformerService;

import java.lang.reflect.InvocationTargetException;

public class TextTransformerServiceTest {
    @Test
    public void test_should_transform_text() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TextTransformerService textTransformerService = new TextTransformerService();
        String[] t = {"capitalize", "lower"};
        Assertions.assertEquals("asd", textTransformerService.transformText("asd", t));
    }
}
