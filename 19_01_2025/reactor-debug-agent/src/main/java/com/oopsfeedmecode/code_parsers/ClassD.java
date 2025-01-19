package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClassD {

    @Autowired
    public ClassE classE;

    private static final Logger logger = LoggerFactory.getLogger(ClassD.class);

    public Mono<String> methodD(String data) {
        logger.info("[methodD] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_D";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodD] Subscribed on thread: {}", Thread.currentThread().getName()))
                .doOnNext(item -> logger.info("[methodD] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(classE::methodE)
                .collectList()
                .map(list -> {
                    logger.info("[methodD] Processed {} items: {} on thread: {}",
                            list.size(), list, Thread.currentThread().getName());
                    return "Processed in Method D: " + list;
                })
                //.doOnError(e -> logger.error("[methodD] Error on thread: {}", Thread.currentThread().getName(), e))
                .doFinally(signalType -> logger.info("[methodD] Completed with signal: {} on thread: {}",
                        signalType, Thread.currentThread().getName()));
    }
}
