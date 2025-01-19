package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
@RestController
public class CodeParsersApplication {
    private static final Logger logger = LoggerFactory.getLogger(CodeParsersApplication.class);

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        if(logger.isDebugEnabled()){
            ReactorDebugAgent.init();
        }
        SpringApplication.run(CodeParsersApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private Mono<String> processRequest() {
        logger.info("[processRequest] Called on thread: {}", Thread.currentThread().getName());

        return Flux.just("data1", "data2", "data3")
                .doOnSubscribe(s -> logger.info("[processRequest] Starting to process data items on thread: {}",
                        Thread.currentThread().getName()))
                // Let's force the entire chain so far to run on a "parallel" scheduler
                // to demonstrate concurrency. You could also do .subscribeOn(...) at the start.
                //.publishOn(Schedulers.parallel())
                .doOnNext(item -> logger.info("[processRequest] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(this::methodA)
                .collectList()
                .map(list -> {
                    logger.info("[processRequest] Successfully processed {} items: {} on thread: {}",
                            list.size(), list, Thread.currentThread().getName());
                    return "Processed: " + list;
                })
                //.doOnError(e -> logger.error("[processRequest] Error occurred on thread: {}", Thread.currentThread().getName(), e))
                .doFinally(signalType -> logger.info("[processRequest] Completed with signal: {} on thread: {}",
                        signalType, Thread.currentThread().getName()));
    }

    private Mono<String> methodA(String data) {
        logger.info("[methodA] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_A";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodA] Subscribed on thread: {}", Thread.currentThread().getName()))
                // Switch to boundedElastic to demonstrate thread change
                //.publishOn(Schedulers.boundedElastic())
                .doOnNext(item -> logger.info("[methodA] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(this::methodB)
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

    private Mono<String> methodB(String data) {
        logger.info("[methodB] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_B";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodB] Subscribed on thread: {}", Thread.currentThread().getName()))
                // Switch to parallel to demonstrate another thread change
                //.publishOn(Schedulers.parallel())
                .doOnNext(item -> logger.info("[methodB] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(this::methodC)
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

    private Mono<String> methodC(String data) {
        logger.info("[methodC] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_C";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodC] Subscribed on thread: {}", Thread.currentThread().getName()))
                // Switch to single() for variety
                //.publishOn(Schedulers.single())
                .doOnNext(item -> logger.info("[methodC] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(this::methodD)
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

    private Mono<String> methodD(String data) {
        logger.info("[methodD] Invoked with data: {} on thread: {}", data, Thread.currentThread().getName());
        String newData = data + "_D";

        return Flux.just(newData)
                .doOnSubscribe(s -> logger.info("[methodD] Subscribed on thread: {}", Thread.currentThread().getName()))
                // Let's do a publishOn parallel again
                //.publishOn(Schedulers.parallel())
                .doOnNext(item -> logger.info("[methodD] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(this::methodE)
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

    private Mono<String> methodE(String data) {
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