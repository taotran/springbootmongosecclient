package com.pycogroup.taotran.springbootmongosec.client;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class MvcTest {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController());
    }
}
