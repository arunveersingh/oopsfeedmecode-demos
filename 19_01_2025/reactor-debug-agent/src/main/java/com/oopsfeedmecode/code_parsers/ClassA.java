package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClassA {

    @Autowired
    public ClassB classB;

    private static final Logger logger = LoggerFactory.getLogger(ClassA.class);

    public Mono<String> methodA(String data) {
        logger.info("[methodA] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_A";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodA] Subscribed on thread: {}", Thread.currentThread().getName()))
                .doOnNext(item -> logger.info("[methodA] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(classB::methodB)
                .collectList()
                .map(list -> {
                    logger.info("[methodA] Processed {} items: {} on thread: {}",
                            list.size(), list, Thread.currentThread().getName());
                    return "Processed in Method A: " + list;
                })
                //.doOnError(e -> logger.error("[methodA] Error on thread: {}", Thread.currentThread().getName(), e))
                .doFinally(signalType -> logger.info("[methodA] Completed with signal: {} on thread: {}",
                        signalType, Thread.currentThread().getName()));
    }
}
