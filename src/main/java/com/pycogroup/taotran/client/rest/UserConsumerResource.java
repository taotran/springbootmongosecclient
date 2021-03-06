package com.pycogroup.taotran.client.rest;

import com.pycogroup.taotran.client.service.UserService;
import com.pycogroup.taotran.client.config.custom.EnableAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.pycogroup.taotran.client.util.ConsumerUtils.buildHttpRequest;

@RestController
@RequestMapping("/client/api/v1/users")
public class UserConsumerResource {

    private static final String USER_LIST = "http://localhost:8080/api/v1/users";

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Object> consumeUsers(@EnableAuthentication(adminAuth = true) String authString) {

        return userService.findAll(buildHttpRequest(authString));
    }

    @PostMapping
    public void saveUsers(@EnableAuthentication(adminAuth = true) String authString, @RequestBody Object user) {

        final HttpEntity<Object> httpEntity = buildHttpRequest(authString, user);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(USER_LIST, httpEntity, String.class);

        System.out.println(responseEntity);
    }

}
