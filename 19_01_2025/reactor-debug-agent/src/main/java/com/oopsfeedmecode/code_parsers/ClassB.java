package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClassB {

    private static final Logger logger = LoggerFactory.getLogger(ClassB.class);

    @Autowired
    public ClassC classC;

    public Mono<String> methodB(String data) {
        logger.info("[methodB] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_B";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodB] Subscribed on thread: {}", Thread.currentThread().getName()))
                // Switch to parallel to demonstrate another thread change
                //.publishOn(Schedulers.parallel())
                .doOnNext(item -> logger.info("[methodB] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(classC::methodC)
                .collectList()
                .map(list -> {
                    logger.info("[methodB] Processed {} items: {} on thread: {}",
                            list.size(), list, Thread.currentThread().getName());
                    return "Processed in Method B: " + list;
                })
                //.doOnError(e -> logger.error("[methodB] Error on thread: {}", Thread.currentThread().getName(), e))
                .doFinally(signalType -> logger.info("[methodB] Completed with signal: {} on thread: {}",
                        signalType, Thread.currentThread().getName()));
    }
}
