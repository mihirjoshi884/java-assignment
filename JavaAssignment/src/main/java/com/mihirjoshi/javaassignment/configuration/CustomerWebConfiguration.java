package com.mihirjoshi.javaassignment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class CustomerWebConfiguration {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/web/views/"); // Set the prefix for JSP files
        resolver.setSuffix(".jsp"); // Set the suffix for JSP files
        return resolver;
    }

}
