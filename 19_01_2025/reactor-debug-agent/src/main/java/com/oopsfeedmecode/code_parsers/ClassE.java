package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClassE {

    private static final Logger logger = LoggerFactory.getLogger(ClassE.class);

    public Mono<String> methodE(String data) {
        logger.info("[methodE] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data; // Not adding anything in E, just for demonstration

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodE] Subscribed on thread: {}", Thread.currentThread().getName()))
                // We'll stay on the same thread here (no publishOn) to see the difference
                .doOnNext(item -> logger.info("[methodE] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(d -> {
                    // Simulate an error if the data matches a certain pattern
                    if ("data2_A_B_C_D".equals(d)) {
                        logger.warn("[methodE] Simulated error for: {} on thread: {}", d, Thread.currentThread().getName());
                        return Mono.error(new RuntimeException("Simulated error in Method E for " + d));
                    }
                    return Mono.just("[methodE] Successfully processed: " + d);
                })
                .collectList()
                .map(list -> {
                    logger.info("[methodE] Processed {} items: {} on thread: {}",
                            list.size(), list, Thread.currentThread().getName());
                    return "Processed in Method E: " + list;
                })
                //.doOnError(e -> logger.error("[methodE] Error on thread: {}", Thread.currentThread().getName(), e))
                .doFinally(signalType -> logger.info("[methodE] Completed with signal: {} on thread: {}",
                        signalType, Thread.currentThread().getName()));
    }
}
