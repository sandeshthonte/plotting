package com.arundhati.backend.plotting.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import java.util.logging.Filter;

@Configuration
public class FilterPriority {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new JwtAuthenticationFilter());
        registrationBean.addUrlPatterns("/admin/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
