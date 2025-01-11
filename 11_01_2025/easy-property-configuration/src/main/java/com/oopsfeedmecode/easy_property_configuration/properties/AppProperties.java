package com.oopsfeedmecode.easy_property_configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private boolean cache;

    private SystemConfig system;
    private AdvancedConfig advancedConfig;
    private List<Tenant> tenant;

    // Getters and Setters
    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public SystemConfig getSystem() {
        return system;
    }

    public void setSystem(SystemConfig system) {
        this.system = system;
    }

    public AdvancedConfig getAdvancedConfig() {
        return advancedConfig;
    }

    public void setAdvancedConfig(AdvancedConfig advancedConfig) {
        this.advancedConfig = advancedConfig;
    }

    public List<Tenant> getTenant() {
        return tenant;
    }

    public void setTenant(List<Tenant> tenant) {
        this.tenant = tenant;
    }

    public class Tenant {
        private String name;
        private List<Company> company;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Company> getCompany() {
            return company;
        }

        public void setCompany(List<Company> company) {
            this.company = company;
        }
    }

    public class Company {
        private String code;
        private String propertyA;
        private String propertyB;
        private Routing routing;
        private List<Repo> repo;
        private Metadata metadata;

        // Getters and Setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPropertyA() {
            return propertyA;
        }

        public void setPropertyA(String propertyA) {
            this.propertyA = propertyA;
        }

        public String getPropertyB() {
            return propertyB;
        }

        public void setPropertyB(String propertyB) {
            this.propertyB = propertyB;
        }

        public Routing getRouting() {
            return routing;
        }

        public void setRouting(Routing routing) {
            this.routing = routing;
        }

        public List<Repo> getRepo() {
            return repo;
        }

        public void setRepo(List<Repo> repo) {
            this.repo = repo;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public void setMetadata(Metadata metadata) {
            this.metadata = metadata;
        }
    }

    public class Routing {

        private List<Channel> channel;

        // Getters and Setters
        public List<Channel> getChannel() {
            return channel;
        }

        public void setChannel(List<Channel> channel) {
            this.channel = channel;
        }
    }

    public class Metadata {
        private String region;
        private String environment;
        private Map<String, String> tags;
        private List<String> categories;
        private Compliance compliance;

        // Getters and Setters
        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getEnvironment() {
            return environment;
        }

        public void setEnvironment(String environment) {
            this.environment = environment;
        }

        public Map<String, String> getTags() {
            return tags;
        }

        public void setTags(Map<String, String> tags) {
            this.tags = tags;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public Compliance getCompliance() {
            return compliance;
        }

        public void setCompliance(Compliance compliance) {
            this.compliance = compliance;
        }
    }

    public class AdvancedConfig {
        private LoggingConfig logging;

        private TasksConfig tasks;

        private FeatureFlags featureFlags;

        // Getters and Setters
        public LoggingConfig getLogging() {
            return logging;
        }

        public void setLogging(LoggingConfig logging) {
            this.logging = logging;
        }

        public TasksConfig getTasks() {
            return tasks;
        }

        public void setTasks(TasksConfig tasks) {
            this.tasks = tasks;
        }

        public FeatureFlags getFeatureFlags() {
            return featureFlags;
        }

        public void setFeatureFlags(FeatureFlags featureFlags) {
            this.featureFlags = featureFlags;
        }
    }

    public class Authentication {
        private String type;
        private String username;
        private String password;
        private String token;
        private Map<String, String> additional;

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Map<String, String> getAdditional() {
            return additional;
        }

        public void setAdditional(Map<String, String> additional) {
            this.additional = additional;
        }
    }

    public class Channel {
        private String name;
        private String type;
        private String endpoint;
        private Map<String, String> headers;

        private Authentication authentication;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        public Authentication getAuthentication() {
            return authentication;
        }

        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }

    public class Compliance {
        private List<String> standards;
        private Map<String, Boolean> requirements;
        private String lastAuditDate;
        private String nextAuditDate;

        // Getters and Setters
        public List<String> getStandards() {
            return standards;
        }

        public void setStandards(List<String> standards) {
            this.standards = standards;
        }

        public Map<String, Boolean> getRequirements() {
            return requirements;
        }

        public void setRequirements(Map<String, Boolean> requirements) {
            this.requirements = requirements;
        }

        public String getLastAuditDate() {
            return lastAuditDate;
        }

        public void setLastAuditDate(String lastAuditDate) {
            this.lastAuditDate = lastAuditDate;
        }

        public String getNextAuditDate() {
            return nextAuditDate;
        }

        public void setNextAuditDate(String nextAuditDate) {
            this.nextAuditDate = nextAuditDate;
        }
    }

    public class ExternalCollector {
        private String name;
        private String apiKey;
        private String endpoint;
        private String token;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


    public class FeatureFlags {
        private boolean enableMultiRegionSupport;
        private List<String> betaFeatures;
        private List<String> stableFeatures;

        // Getters and Setters
        public boolean isEnableMultiRegionSupport() {
            return enableMultiRegionSupport;
        }

        public void setEnableMultiRegionSupport(boolean enableMultiRegionSupport) {
            this.enableMultiRegionSupport = enableMultiRegionSupport;
        }

        public List<String> getBetaFeatures() {
            return betaFeatures;
        }

        public void setBetaFeatures(List<String> betaFeatures) {
            this.betaFeatures = betaFeatures;
        }

        public List<String> getStableFeatures() {
            return stableFeatures;
        }

        public void setStableFeatures(List<String> stableFeatures) {
            this.stableFeatures = stableFeatures;
        }
    }
    public class LoggingConfig {
        private String level;

        private List<ExternalCollector> externalCollectors;

        // Getters and Setters
        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<ExternalCollector> getExternalCollectors() {
            return externalCollectors;
        }

        public void setExternalCollectors(List<ExternalCollector> externalCollectors) {
            this.externalCollectors = externalCollectors;
        }
    }

    public class Repo {
        private String name;
        private String type;
        private String url;
        private Authentication authentication;
        private Map<String, String> properties;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Authentication getAuthentication() {
            return authentication;
        }

        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }

        public Map<String, String> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, String> properties) {
            this.properties = properties;
        }
    }

    public class Schedule {
        private String name;
        private String cron;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }
    }

    public class SystemConfig {
        private int timeout;
        private int retry;

        private String sessionCache;

        // Getters and Setters
        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public int getRetry() {
            return retry;
        }

        public void setRetry(int retry) {
            this.retry = retry;
        }

        public String getSessionCache() {
            return sessionCache;
        }

        public void setSessionCache(String sessionCache) {
            this.sessionCache = sessionCache;
        }
    }
    public class TasksConfig {
        private int concurrencyLimit;

        private List<Schedule> schedules;

        // Getters and Setters
        public int getConcurrencyLimit() {
            return concurrencyLimit;
        }

        public void setConcurrencyLimit(int concurrencyLimit) {
            this.concurrencyLimit = concurrencyLimit;
        }

        public List<Schedule> getSchedules() {
            return schedules;
        }

        public void setSchedules(List<Schedule> schedules) {
            this.schedules = schedules;
        }
    }



}


