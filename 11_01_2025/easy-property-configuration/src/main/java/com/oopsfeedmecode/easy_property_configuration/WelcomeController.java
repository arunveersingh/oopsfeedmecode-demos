package com.oopsfeedmecode.easy_property_configuration;

import com.oopsfeedmecode.easy_property_configuration.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WelcomeController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    public AppProperties  appProperties;

    @GetMapping("/app")
    public String app() {
        log.info("system: {}, tenants: {}", appProperties);
        return appProperties.toString();
    }

    @GetMapping("/tenants")
    public List<AppProperties.Tenant> getTenants() {
        return appProperties.getTenants();
    }

    @GetMapping("/partners")
    public List<AppProperties.Partner> getAllPartners() {
        // Flatten all tenant partners
        return appProperties.getTenants().stream()
                .flatMap(tenant -> tenant.getPartners().stream())
                .toList();
    }
}