package com.gw.apex.hack.yabe.app;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gw.apex.hack.yabe.domain.AnyDomainOne;
import com.gw.apex.hack.yabe.repo.AnyDomainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.Properties;

/**
 * @author dfarrell on 12/12/2016.
 */

@SpringBootApplication
@EnableGemfireRepositories
@Configuration
@EnableAutoConfiguration
public class App {

    @Bean
    /**
     * this allow us to parse non HTML5 HTML, otherwise we get lots of tag errors.
     */
    public ServletContextTemplateResolver defaultTemplateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("LEGACYHTML5");
        resolver.setOrder(1);
        return resolver;
    }

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
