package com.playtika.test.kafka.properties;

import com.playtika.test.common.properties.CommonContainerProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static java.lang.String.format;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("embedded.zookeeper")
public class ZookeeperConfigurationProperties extends CommonContainerProperties {
    protected int zookeeperContainerPort = 2181;
    protected String zookeeperContainerHost = "localhost";

    protected FileSystemBind fileSystemBind = new FileSystemBind();

    @Data
    public static final class FileSystemBind {
        private boolean enabled = false;
        private String dataFolder = "target/embedded-zk-data";
        private String txnLogsFolder = "target/embedded-zk-txn-logs";
    }

    public String getZookeeperConnect() {
        return format("%s:%d", getZookeeperContainerHost(), getZookeeperContainerPort());
    }
}