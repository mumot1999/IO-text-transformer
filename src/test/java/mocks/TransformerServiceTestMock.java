package mocks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import pl.put.poznan.transformer.logic.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.stream.Stream;

public class TransformerServiceTestMock {

    @Test
    public void functionCallTest()throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{

        TransformerProvider mock = mock(TransformerProvider.class);
        TextTransformerService service = new TextTransformerService(mock);

        String t[] = {"capitalize", "upper", "lower", "latex"};
        String text = "foo";

        doReturn(CapitalizeTransformer.class).when(mock).getTransformer("capitalize");
        doReturn(LowerCaseTransformer.class).when(mock).getTransformer("lower");
        doReturn(UpperCaseTransformer.class).when(mock).getTransformer("upper");
        doReturn(LatexTransformer.class).when(mock).getTransformer("latex");

        service.transformText(text, t);

        verify(mock, times(1)).getTransformer(t[0]);
        verify(mock, times(1)).getTransformer(t[1]);
        verify(mock, times(1)).getTransformer(t[2]);
        verify(mock, times(1)).getTransformer(t[3]);
    }

    @Test
    public void multipleFunctionCallTest()throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{

        TransformerProvider mock = mock(TransformerProvider.class);
        TextTransformerService service = new TextTransformerService(mock);

        String t[] = {"capitalize", "capitalize"};
        String text = "foo";

        doReturn(CapitalizeTransformer.class).when(mock).getTransformer("capitalize");

        service.transformText(text, t);

        verify(mock, times(2)).getTransformer("capitalize");
    }

    @Test
    public void transformationOrderTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{

        TransformerProvider mock = mock(TransformerProvider.class);
        InOrder inOrder = inOrder(mock);

        TextTransformerService service = new TextTransformerService(mock);

        String t[] = {"capitalize", "upper", "lower"};
        String text = "foo";

        doReturn(CapitalizeTransformer.class).when(mock).getTransformer("capitalize");
        doReturn(UpperCaseTransformer.class).when(mock).getTransformer("upper");
        doReturn(LowerCaseTransformer.class).when(mock).getTransformer("lower");

        service.transformText(text, t);



        inOrder.verify(mock).getTransformer(t[0]);
        inOrder.verify(mock).getTransformer(t[1]);
        inOrder.verify(mock).getTransformer(t[2]);
    }

    static Stream<Arguments> transformProviderCheckArgumentsProvider() {
        return Stream.of(
                arguments(new String[]{"upper", "upper", "upper"}, new HashMap<String, Integer>(){{put("upper", 3);}}),
                arguments(new String[]{"upper", "lower", "upper"}, new HashMap<String, Integer>(){{put("upper", 2);put("lower", 1);}}),
                arguments(new String[]{"capitalize", "lower", "capitalize"}, new HashMap<String, Integer>(){{put("capitalize", 2);put("lower", 1);}}),
                arguments(new String[]{"acronym", "repetition_elimination", "text_to_acronym", "upper", "latex"}, new HashMap<String, Integer>(){{put("acronym", 1);put("repetition_elimination", 1);put("text_to_acronym", 1);put("upper", 1);put("latex", 1);}}),
                arguments(new String[]{"a"}, new HashMap<String, Integer>(){{put("a", 1);}}),
                arguments(new String[]{"b"}, new HashMap<String, Integer>(){{put("b", 1);}}),
                arguments(new String[]{"c"}, new HashMap<String, Integer>(){{put("c", 1);}}),
                arguments(new String[]{"d"}, new HashMap<String, Integer>(){{put("d", 1);}})
        );
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("transformProviderCheckArgumentsProvider")
    void checkProvider(String[] transformers, HashMap<String, Integer> shouldBeCalled) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        TransformerProvider mock = mock(TransformerProvider.class);
        doReturn(CapitalizeTransformer.class).when(mock).getTransformer(any());

        TextTransformerService service = new TextTransformerService(mock);
        service.transformText("foo", transformers);

        for(String s : shouldBeCalled.keySet()){
            verify(mock, times(shouldBeCalled.get(s))).getTransformer(s);
        }

    }

}
