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
public class CodeParsersApplication_1 {
    private static final Logger logger = LoggerFactory.getLogger(CodeParsersApplication_1.class);

    public static void main(String[] args) {
        // Uncomment to enable ReactorDebugAgent
        //ReactorDebugAgent.init();
        SpringApplication.run(CodeParsersApplication_1.class, args);
    }

    @GetMapping("/process")
    private Mono<String> processRequest() {
        return Flux.just("data1", "data2", "data3")
                .doOnSubscribe(subscription -> logger.info("Starting to process data items"))
                .flatMap(this::processData)
                .flatMap(this::addMetadata)
                .flatMap(this::validateData)
                .flatMap(this::saveToDatabase)
                .collectList()
                .map(list -> {
                    logger.info("Successfully processed {} items: {}", list.size(), list);
                    return "Processed: " + list;
                })
                .doOnError(e -> logger.error("Error occurred during processing", e))
                .doFinally(signalType -> logger.info("Processing completed with signal: {}", signalType));
    }

    private Mono<String> processData(String data) {
        return Mono.defer(() -> {
            logger.debug("Processing data item: {}", data);
            if ("data2".equals(data)) {
                logger.warn("Encountered data2 - generating simulated error");
                return Mono.error(new RuntimeException("Simulated error for data2"));
            }
            String result = data.toUpperCase();
            logger.debug("Successfully transformed data {} to {}", data, result);
            return Mono.just(result);
        });
    }

    private Mono<String> addMetadata(String data) {
        return Mono.defer(() -> {
            logger.debug("Adding metadata to: {}", data);
            String enrichedData = data + "_META";
            if ("DATA3_META".equals(enrichedData)) {
                logger.warn("Simulated nested error for: {}", enrichedData);
                return Mono.error(new RuntimeException("Nested error for " + enrichedData));
            }
            logger.debug("Metadata added: {}", enrichedData);
            return Mono.just(enrichedData);
        });
    }

    private Mono<String> validateData(String data) {
        return Mono.defer(() -> {
            logger.debug("Validating data: {}", data);
            if (data.length() > 15) {
                logger.warn("Validation failed for: {}", data);
                return Mono.error(new RuntimeException("Validation failed for " + data));
            }
            logger.debug("Validation successful for: {}", data);
            return Mono.just(data);
        });
    }

    private Mono<String> saveToDatabase(String data) {
        return Mono.defer(() -> {
            logger.debug("Saving data to database: {}", data);
            // Simulate database save
            if ("DATA1_META".equals(data)) {
                logger.warn("Database error for: {}", data);
                return Mono.error(new RuntimeException("Database save error for " + data));
            }
            logger.debug("Data successfully saved: {}", data);
            return Mono.just("DB_SAVE_SUCCESS: " + data);
        });
    }
}