package com.gw.apex.hack.yabe;

import com.gemstone.gemfire.cache.GemFireCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.Properties;

/**
 * @author dfarrell on 12/12/2016.
 */

@SpringBootApplication
@EnableGemfireRepositories
@Configuration
public class App {

    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", "DataGemFireApplication");
        gemfireProperties.setProperty("mcast-port", "0");
        gemfireProperties.setProperty("log-level", "config");
        return gemfireProperties;
    }

    @Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        gemfireCache.setClose(true);
        gemfireCache.setProperties(gemfireProperties());
        return gemfireCache;
    }

    @Bean
    LocalRegionFactoryBean<String, AnyDomainOne> testRegion(final GemFireCache cache) {
        LocalRegionFactoryBean<String, AnyDomainOne> testRegion = new LocalRegionFactoryBean<>();
        testRegion.setCache(cache);
        testRegion.setClose(false);
        testRegion.setName("test");
        testRegion.setPersistent(false);
        return testRegion;
    }

    @Autowired
    AnyDomainRepo anyDomainRepo;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
