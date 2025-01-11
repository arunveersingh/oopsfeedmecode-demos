package com.oopsfeedmecode.easy_property_configuration;

import com.oopsfeedmecode.easy_property_configuration.properties.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    // add logger
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WelcomeController.class);

    private final AppProperties appProperties;

    public WelcomeController(AppProperties welcomeProperties) {
        this.appProperties = welcomeProperties;
    }

    @GetMapping("/welcome")
    public AppProperties getAppProperties() {
        return appProperties;
    }
}