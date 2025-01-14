package com.oopsfeedmecode.easy_property_configuration.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Why do we need this class?
 * To be sure properties are loading correctly
 * Imagine someone coming to you say that application is
 * not running fine, just use the props their environment
 * and see how it goes...
 *
 * To ensure application always have default-values committed.
 * Let's say someone changes the localhost with her personal ip
 * in commit, these tests will catch it and fail the build.
 */
@SpringBootTest
class AppPropertiesTest {

    @Autowired
    private AppProperties appProperties;

    @Test
    void testSystemProperties() {
        assertNotNull(appProperties.getSystem());
        assertEquals("test-app", appProperties.getSystem().getName());
        assertEquals("2.0.0", appProperties.getSystem().getVersion());
    }

    @Test
    void testTenantsConfiguration() {
        assertNotNull(appProperties.getTenants());
        assertEquals(2, appProperties.getTenants().size());

        // Test tenant-a configuration
        var tenantA = appProperties.getTenants().get(0);
        assertEquals("tenant-a", tenantA.getName());
        assertNotNull(tenantA.getPartners());
        assertEquals(3, tenantA.getPartners().size());

        // Test tenant-a's partners
        var partners = tenantA.getPartners();

        // Test first partner
        assertEquals("partner-1", partners.get(0).getId());
        assertNotNull(partners.get(0).getContract());
        assertEquals("01-01-25", partners.get(0).getContract().get(0).get("start"));
        assertEquals("30-12-99", partners.get(0).getContract().get(1).get("end"));

        // Test second partner (partner-1 without contract)
        assertEquals("partner-1", partners.get(1).getId());
        assertNull(partners.get(1).getContract());

        // Test third partner
        assertEquals("partner-2", partners.get(2).getId());
        assertNotNull(partners.get(2).getContract());
        assertEquals("01-01-25", partners.get(2).getContract().get(0).get("start"));
        assertEquals("31-12-99", partners.get(2).getContract().get(1).get("end"));

        // Test tenant-b configuration
        var tenantB = appProperties.getTenants().get(1);
        assertEquals("tenant-b", tenantB.getName());
        assertTrue(tenantB.getPartners().get(0).getId().equals("partner-3"));

    }
}
