package toplchx.example.webflux.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import toplchx.example.webflux.bean.Msg;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/server")
public class ReactiveController {

    @GetMapping("/echo/{id}")
    private Mono<Msg> echo(@PathVariable String id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("=========="+id);
        return Mono.just(Msg.builder().msg(id).build());
    }
}
