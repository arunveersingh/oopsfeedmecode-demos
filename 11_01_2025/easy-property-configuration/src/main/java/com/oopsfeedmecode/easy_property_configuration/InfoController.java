package com.oopsfeedmecode.easy_property_configuration;

import com.oopsfeedmecode.easy_property_configuration.properties.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class InfoController {

    private final AppProperties appProperties;

    public InfoController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    /**
     * Returns the entire AppProperties object.
     * WARNING: This can sometimes trigger Jackson errors if proxy fields are present.
     * For safety, consider returning a DTO or ignoring proxy fields.
     */
    @GetMapping("/app")
    public String getAppProperties() {
        return appProperties.toString();
    }

    /**
     * Returns only the "system" block, e.g.:
     *   { "name": "test-app", "version": "2.0.0" }
     */
    @GetMapping("/system")
    public AppProperties.SystemProps getSystemProps() {
        return appProperties.getSystem();
    }

    /**
     * Returns the top-level metadata map:
     *   { "key1": "value1", "key2": "value2" }
     */
    @GetMapping("/metadata")
    public Map<String, String> getMetadata() {
        return appProperties.getMetadata();
    }

    /**
     * Returns all tenants with their partners/customers.
     */
    @GetMapping("/tenants")
    public List<AppProperties.Tenant> getTenants() {
        return appProperties.getTenants();
    }

    /**
     * Flattens out all partners from all tenants into a single list.
     */
    @GetMapping("/all-partners")
    public List<AppProperties.Partner> getAllPartners() {
        return appProperties.getTenants().stream()
                .filter(tenant -> tenant.getPartners() != null)
                .flatMap(tenant -> tenant.getPartners().stream())
                .collect(Collectors.toList());
    }

    /**
     * Flattens out all customers from all tenants into a single list.
     */
    @GetMapping("/all-customers")
    public List<AppProperties.Customer> getAllCustomers() {
        return appProperties.getTenants().stream()
                .filter(tenant -> tenant.getCustomers() != null)
                .flatMap(tenant -> tenant.getCustomers().stream())
                .collect(Collectors.toList());
    }
}
