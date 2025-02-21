package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(OutputCaptureExtension.class)
public class DemoApplicationOutputCaptureTest {

    @AfterEach
    void reset() throws Exception {
        LogManager.getLogManager().readConfiguration();
    }

    @Test
    public void testLoggingOutput(CapturedOutput output) {
        // Run your code that produces log output
        DemoApplication.main(new String[]{});

        // Assert that expected log messages are present in the captured output
        String captured = output.getOut();
        assertTrue(captured.contains("Starting app..."), "Expected 'Starting app...' log not found");
        assertTrue(captured.contains("... app started."), "Expected '... app started.' log not found");
    }
}
