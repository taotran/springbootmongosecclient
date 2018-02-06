package com.pycogroup.taotran.springbootmongosec.client.service;

import com.pycogroup.taotran.springbootmongosec.client.config.custom.EnableAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.pycogroup.taotran.springbootmongosec.client.util.ConsumerUtils.buildHttpRequest;
import static com.pycogroup.taotran.springbootmongosec.client.util.ConsumerUtils.toList;

@RestController
@RequestMapping("/client/api/users")
public class UserConsumerResource {

    private static final String USER_LIST = "http://localhost:8080/api/v1/users";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Object> consumeUsers(@EnableAuthentication(adminAuth = true) String authString) {

        final ResponseEntity<Object[]> objects = restTemplate.exchange(USER_LIST, HttpMethod.GET, buildHttpRequest(authString), Object[].class);

        return toList(objects.getBody());
    }

    @PostMapping
    public void saveUsers(@EnableAuthentication(adminAuth = true) String authString, @RequestBody Object user) {

        final HttpEntity<Object> httpEntity = buildHttpRequest(authString, user);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(USER_LIST, httpEntity, String.class);

        System.out.println(responseEntity);
    }

}
