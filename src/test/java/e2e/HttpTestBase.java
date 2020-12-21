package e2e;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.impl.client.CloseableHttpClient;

public class HttpTestBase {
    static final String URI = "http://127.0.0.1:9091/transform";

    protected static CloseableHttpClient httpClient;
    protected ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() {
        httpClient = HttpClientBuilder.create().build();
    }

    @AfterAll
    static void tearDownAll() throws IOException {
        httpClient.close();
    }

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModules(new JavaTimeModule(), new Jdk8Module());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    protected HttpGet buildRequest(String string, String[] transforms) throws UnsupportedEncodingException, JsonProcessingException {
        HttpGet httpGet = new HttpGet(URI+ "?text=" + string + "&transforms[]=" + String.join(",", transforms));
        httpGet.setHeader("Content-type", "application/json");
        return httpGet;
    }

}
