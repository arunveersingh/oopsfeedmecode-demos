package com.oopsfeedmecode.easy_property_configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Binds to properties under "app" in application.yaml
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private SystemProps system;
    private List<Tenant> tenants;
    private Map<String, String> metadata; // for app.metadata key-value pairs

    public SystemProps getSystem() {
        return system;
    }

    public void setSystem(SystemProps system) {
        this.system = system;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "AppProperties{" +
                "system=" + system +
                ", tenants=" + tenants +
                ", metadata=" + metadata +
                '}';
    }

    // -----------------------------------------------------
    // Nested Classes
    // -----------------------------------------------------

    /**
     * Maps the "system" sub-property:
     *   app.system.name
     *   app.system.version
     */
    public static class SystemProps {
        private String name;
        private String version;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }
        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "SystemProps{" +
                    "name='" + name + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }

    /**
     * Maps each "tenant" in the list:
     *   app.tenants -> List<Tenant>
     */
    public static class Tenant {
        private String name;
        private List<Partner> partners;
        private List<Customer> customers; // present in tenant-b only, may be null or empty for others

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public List<Partner> getPartners() {
            return partners;
        }
        public void setPartners(List<Partner> partners) {
            this.partners = partners;
        }

        public List<Customer> getCustomers() {
            return customers;
        }
        public void setCustomers(List<Customer> customers) {
            this.customers = customers;
        }

        @Override
        public String toString() {
            return "Tenant{" +
                    "name='" + name + '\'' +
                    ", partners=" + partners +
                    ", customers=" + customers +
                    '}';
        }
    }

    /**
     * Maps each "partner" in the list:
     *   app.tenants[x].partners -> List<Partner>
     *
     * "contract" is a list of single-key maps:
     *   - start: "01-01-25"
     *   - end: "31-12-99"
     */
    public static class Partner {
        private String id;
        // contract can be parsed as a list of single-key maps,
        // each mapping to a key like "start"/"end".
        private List<Map<String, String>> contract;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public List<Map<String, String>> getContract() {
            return contract;
        }
        public void setContract(List<Map<String, String>> contract) {
            this.contract = contract;
        }

        @Override
        public String toString() {
            return "Partner{" +
                    "id='" + id + '\'' +
                    ", contract=" + contract +
                    '}';
        }
    }

    /**
     * Maps each "customer" in the list:
     *   app.tenants[x].customers -> List<Customer>
     */
    public static class Customer {
        private String id;
        private Boolean subscription;
        private Boolean renewal;
        // 'additional' is a nested object with key1, key2, etc.
        private Map<String, String> additional;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public Boolean getSubscription() {
            return subscription;
        }
        public void setSubscription(Boolean subscription) {
            this.subscription = subscription;
        }

        public Boolean getRenewal() {
            return renewal;
        }
        public void setRenewal(Boolean renewal) {
            this.renewal = renewal;
        }

        public Map<String, String> getAdditional() {
            return additional;
        }
        public void setAdditional(Map<String, String> additional) {
            this.additional = additional;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id='" + id + '\'' +
                    ", subscription=" + subscription +
                    ", renewal=" + renewal +
                    ", additional=" + additional +
                    '}';
        }
    }
}
