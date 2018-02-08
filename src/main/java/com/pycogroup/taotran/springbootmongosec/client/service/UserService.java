package com.pycogroup.taotran.springbootmongosec.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.pycogroup.taotran.springbootmongosec.client.util.ConsumerUtils.toList;

@Service
public class UserService {

    private static final String USER_LIST = "http://localhost:8080/api/v1/users";

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public List<Object> findAll(HttpEntity requestEntity) {
        final ResponseEntity<Object[]> objects = restTemplate.exchange(USER_LIST, HttpMethod.GET, requestEntity, Object[].class);

        return toList(objects.getBody());
    }

    public List<Object> reliable(HttpEntity requestEntity) {
        return Arrays.asList("Core application is currently down!!!");
    }
}
