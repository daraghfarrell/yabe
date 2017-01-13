package com.gw.apex.hack.yabe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * @author dfarrell on 12/12/2016.
 */

@SpringBootApplication
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

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }
}
