package mocks;

import org.apache.commons.collections.bag.TransformedSortedBag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.InOrder;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.logic.base.Text;
import pl.put.poznan.transformer.logic.base.TextTransformerInterface;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

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
}
