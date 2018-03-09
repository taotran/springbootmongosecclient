package com.pycogroup.taotran;

import com.pycogroup.taotran.client.App;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = App.class
)
@Ignore
@SuppressWarnings("all")
public class BaseAppTest {


    @Before
    public void setUp() throws Exception {

    }
}
