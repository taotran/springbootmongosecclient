package com.pycogroup.taotran.client.config;


import com.pycogroup.taotran.client.config.custom.EnableAuthenticationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@PropertySource(value = "kafka-config.properties")
public class ApplicationConfig extends WebMvcConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(new EnableAuthenticationArgumentResolver());

        super.addArgumentResolvers(argumentResolvers);
    }
}
