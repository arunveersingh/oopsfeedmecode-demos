package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.tools.agent.ReactorDebugAgent;

//@SpringBootApplication
//@RestController
public class CodeParsersApplication_4 {
    private static final Logger logger = LoggerFactory.getLogger(CodeParsersApplication_4.class);

    @Autowired
    public ClassA classA;

    public static void main(String[] args) {
        // Enable Reactor Debug Agent for development or testing
          //ReactorDebugAgent.init();
        // Hooks.onOperatorDebug();
        SpringApplication.run(CodeParsersApplication_4.class, args);
    }

    //@EventListener(ApplicationReadyEvent.class)
    @GetMapping("/process")
    private Mono<String> processRequest() {
        logger.info("[processRequest] Called on thread: {}", Thread.currentThread().getName());

        return Flux.just("data1", "data2", "data3")
                .doOnSubscribe(s -> logger.info("[processRequest] Starting to process data items on thread: {}",
                        Thread.currentThread().getName()))
                .doOnNext(item -> logger.info("[processRequest] Emitting: {} on thread: {}",
                        item, Thread.currentThread().getName()))
                .flatMap(classA::methodA)
                .collectList()
                .map(list -> {
                    logger.info("[processRequest] Successfully processed {} items: {} on thread: {}",
                            list.size(), list, Thread.currentThread().getName());
                    return "Processed: " + list;
                })
                .doOnError(e -> logger.error("[processRequest] Error occurred on thread: {}",
                        Thread.currentThread().getName(), e))
                .doFinally(signalType -> logger.info("[processRequest] Completed with signal: {} on thread: {}",
                        signalType, Thread.currentThread().getName()));
    }
}