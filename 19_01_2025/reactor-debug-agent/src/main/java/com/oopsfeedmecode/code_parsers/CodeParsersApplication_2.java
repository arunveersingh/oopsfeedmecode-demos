package com.oopsfeedmecode.code_parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@SpringBootApplication
//@RestController
public class CodeParsersApplication_2 {
    private static final Logger logger = LoggerFactory.getLogger(CodeParsersApplication_2.class);

    public static void main(String[] args) {
        // Uncomment to enable ReactorDebugAgent
        //ReactorDebugAgent.init();
        SpringApplication.run(CodeParsersApplication_2.class, args);
    }

    @GetMapping("/process")
    private Mono<String> processRequest() {
        return Flux.just("data1", "data2", "data3")
                .doOnSubscribe(subscription -> logger.info("Starting to process data items"))
                .flatMap(this::methodA)
                .collectList()
                .map(list -> {
                    logger.info("Successfully processed {} items: {}", list.size(), list);
                    return "Processed: " + list;
                })
                .doOnError(e -> logger.error("Error occurred during processing", e))
                .doFinally(signalType -> logger.info("Processing completed with signal: {}", signalType));
    }

    private Mono<String> methodA(String data) {
        logger.debug("Method A called with: {}", data);
        return methodB(data + "_A");
    }

    private Mono<String> methodB(String data) {
        logger.debug("Method B called with: {}", data);
        return methodC(data + "_B");
    }

    private Mono<String> methodC(String data) {
        logger.debug("Method C called with: {}", data);
        return methodD(data + "_C");
    }

    private Mono<String> methodD(String data) {
        logger.debug("Method D called with: {}", data);
        return methodE(data + "_D");
    }

    private Mono<String> methodE(String data) {
        logger.debug("Method E called with: {}", data);
        if ("data2_A_B_C_D".equals(data)) {
            logger.warn("Simulated error in Method E for: {}", data);
            return Mono.error(new RuntimeException("Simulated error in Method E for " + data));
        }
        return Mono.just("Successfully processed: " + data);
    }
}