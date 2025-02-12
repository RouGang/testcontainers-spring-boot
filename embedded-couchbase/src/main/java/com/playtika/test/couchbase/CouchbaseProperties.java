package com.playtika.test.couchbase;

import com.github.dockerjava.api.model.Capability;
import com.playtika.test.common.properties.CommonContainerProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.testcontainers.couchbase.CouchbaseService;

import java.util.Arrays;

import static java.lang.String.format;

/**
 * https://blog.couchbase.com/testing-spring-data-couchbase-applications-with-testcontainers/
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("embedded.couchbase")
public class CouchbaseProperties extends CommonContainerProperties {
    public static final String BEAN_NAME_EMBEDDED_COUCHBASE = "embeddedCouchbase";
    CouchbaseService[] services = new CouchbaseService[]{
            CouchbaseService.INDEX
            , CouchbaseService.KV
            , CouchbaseService.QUERY
            , CouchbaseService.SEARCH};
    // https://hub.docker.com/_/couchbase   "couchbase:community-7.0.0"
    // https://hub.docker.com/r/couchbase/server
    String dockerImage = "couchbase/server:7.0.0";
    int bucketRamMb = 100;
    String bucketType = "couchbase";

    String host = "localhost";
    String user = "Administrator";
    String password = "password";
    String bucket = "test";

    public CouchbaseProperties() {
        this.setCapabilities(Arrays.asList(Capability.NET_ADMIN));
    }

    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Couchbase requires password length >= 6 chars, password=" + password);
        }
        this.password = password;
    }

    public String getCredentials() {
        return format("%s:%s", user, password);
    }
}
