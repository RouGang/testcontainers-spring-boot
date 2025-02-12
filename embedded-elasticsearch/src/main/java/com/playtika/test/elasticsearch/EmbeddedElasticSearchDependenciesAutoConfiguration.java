package com.playtika.test.elasticsearch;

import com.playtika.test.common.spring.DependsOnPostProcessor;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.playtika.test.elasticsearch.ElasticSearchProperties.BEAN_NAME_EMBEDDED_ELASTIC_SEARCH;

@Configuration
@AutoConfigureOrder
@ConditionalOnExpression("${embedded.containers.enabled:true}")
@ConditionalOnClass(Client.class)
@ConditionalOnProperty(name = "embedded.elasticsearch.enabled", matchIfMissing = true)
@AutoConfigureAfter(name = "org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration")
public class EmbeddedElasticSearchDependenciesAutoConfiguration {

    @Bean
    public static BeanFactoryPostProcessor elasticClientDependencyPostProcessor() {
        return new DependsOnPostProcessor(Client.class, new String[]{BEAN_NAME_EMBEDDED_ELASTIC_SEARCH});
    }
}
