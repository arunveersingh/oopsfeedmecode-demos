package com.oopsfeedmecode.easy_property_configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Binds to properties under "app" in application.yaml
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private SystemProps system;
    private List<Tenant> tenants;

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

    /**
     * Maps the "system" sub-property:
     * app.system.name
     * app.system.version
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
     * app.tenants -> List<Tenant>
     */
    public static class Tenant {
        private String name;
        private List<Partner> partners;

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

        @Override
        public String toString() {
            return "Tenant{" +
                    "name='" + name + '\'' +
                    ", partners=" + partners +
                    '}';
        }
    }

    /**
     * Maps each "partner" in the list:
     * app.tenants[x].partners -> List<Partner>
     */
    public static class Partner {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Partner{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppProperties{" +
                "system=" + system +
                ", tenants=" + tenants +
                '}';
    }
}
