package toplchx.example.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import toplchx.example.webflux.bean.Msg;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ReactiveClient1 {

    private static WebClient client = WebClient.create("http://localhost:8080");

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Instant start = Instant.now();
        try {
            for (int i = 0; i < 3; i++) {
                log.info("request：id=" + i);
                client
                        .get()
                        .uri("/server/echo/{id}", String.valueOf(i))
                        .retrieve()
                        .bodyToMono(Msg.class)
                        .subscribe(msg -> log.info("got msg:" + msg), e -> latch.countDown(), latch::countDown);
            }
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Total：" + Duration.between(start, Instant.now()).toMillis() + " millis");
    }

}
