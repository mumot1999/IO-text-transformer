package e2e;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TransformerTest extends HttpTestBase{
    @Test
    public void should_transform_to_upper_case(){
        String[] transformers = {"upper"};

        try {
            httpClient.execute(buildRequest("asd", transformers));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
