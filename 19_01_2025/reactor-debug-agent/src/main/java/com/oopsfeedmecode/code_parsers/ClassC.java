package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClassC {

    private static final Logger logger = LoggerFactory.getLogger(ClassC.class);

    @Autowired
    public ClassD classD;

    public Mono<String> methodC(String data) {
        logger.info("[methodC] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_C";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodC] Subscribed on thread: {}", Thread.currentThread().getName()))
                // Switch to single() for variety
                //.publishOn(Schedulers.single())
                .doOnNext(item -> logger.info("[methodC] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(classD::methodD)
                .collectList()
                .map(list -> {
                    logger.info("[methodC] Processed {} items: {} on thread: {}",
                            list.size(), list, Thread.currentThread().getName());
                    return "Processed in Method C: " + list;
                })
                //.doOnError(e -> logger.error("[methodC] Error on thread: {}", Thread.currentThread().getName(), e))
                .doFinally(signalType -> logger.info("[methodC] Completed with signal: {} on thread: {}",
                        signalType, Thread.currentThread().getName()));
    }
}
