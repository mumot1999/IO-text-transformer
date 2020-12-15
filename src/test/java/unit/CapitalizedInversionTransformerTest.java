package unit;

import org.junit.jupiter.api.*;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;
import static org.assertj.core.api.Assertions.assertThat;

public class CapitalizedInversionTransformerTest {

    private Text text = null;
    private static final String[] testStrings = {
            "DuużOpRoJektów",
            "",
            "DDDDD",
            "ddddd",
            "-1",
            "\n",
            "\t\nalamakotA\t",
            " D D"
    };

    private static final String[] ansStrings = {
            "WótkEjOrPożuud",
            "",
            "DDDDD",
            "ddddd",
            "1-",
            "\n",
            "\tatokamala\n\t",
            "d d "
    };
    private static int testNumber = 0;

    @BeforeEach
    public void setUp(){
        text = new Text(new CapitalizedInversionTransformer(new Text(testStrings[testNumber])).getText());
        System.out.println("Running test " + String.valueOf(testNumber + 1));
    }
    @Test
    public void invCapTest1(){
       Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @Test
    public void invCapTest2(){
        Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @Test
    public void invCapTest3(){
        Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @Test
    public void invCapTest4(){
        Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @Test
    public void invCapTest5(){
        Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @Test
    public void invCapTest6(){
        Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @Test
    public void invCapTest7(){
        Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @Test
    public void invCapTest8(){
        Assertions.assertEquals(ansStrings[testNumber], text.getText());
    }

    @AfterEach
    public void tearDown() {
        text = null;
        testNumber++;

    }
}
