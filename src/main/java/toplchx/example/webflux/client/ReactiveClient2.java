package toplchx.example.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import toplchx.example.webflux.bean.Msg;

import java.time.Duration;
import java.time.Instant;

@Slf4j
public class ReactiveClient2 {
    private static WebClient client = WebClient.create("http://localhost:8080");

    public static void main(String[] args) {
        Instant start = Instant.now();

        Flux.range(1, 3)
                .doOnNext(i -> log.info("request：id=" + i))
                .flatMap(i -> client.get().uri("/server/echo/{id}", String.valueOf(i)).retrieve().bodyToMono(Msg.class))
                .doOnNext(msg -> log.info("got msg:" + msg))
                .blockLast();

        log.info("Total："+ Duration.between(start, Instant.now()).toMillis() + " millis");
    }
}
