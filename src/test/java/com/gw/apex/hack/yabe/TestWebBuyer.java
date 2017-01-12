package com.gw.apex.hack.yabe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author dfarrell on 12/01/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWebBuyer {
    @Autowired
    private TestRestTemplate template;


    @Test
    public void testBuyerPageExists() throws Exception {
        String response = template.getForObject("/buyer", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("<title>Buyer</title>"));
    }

}
