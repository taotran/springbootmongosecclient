package com.pycogroup.taotran.springbootmongosec.client.config;


import com.pycogroup.taotran.springbootmongosec.client.config.custom.EnableAuthenticationArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(new EnableAuthenticationArgumentResolver());

        super.addArgumentResolvers(argumentResolvers);
    }
}
