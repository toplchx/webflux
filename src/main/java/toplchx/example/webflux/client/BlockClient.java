package toplchx.example.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.time.Duration;
import java.time.Instant;

@Slf4j
public class BlockClient {
    public static void main(String[] args) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        Instant start = Instant.now();
        for (int i=0; i<3; i++) {
            try {
                log.info("request：id=" + i);
                CloseableHttpResponse response = client.execute(new HttpGet("http://localhost:8080/server/echo/"+String.valueOf(i)));
                String bodyAsString = EntityUtils.toString(response.getEntity());
                log.info("got msg:" + bodyAsString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("Total："+ Duration.between(start, Instant.now()).toMillis() + " millis");
    }
}
