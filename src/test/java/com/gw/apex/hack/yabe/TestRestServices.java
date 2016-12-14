package com.gw.apex.hack.yabe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author dfarrell on 12/12/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestServices {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testRestController() throws Exception {
        String response = template.getForObject("/anydata", String.class);
        assertThat(response, containsString("{\"name\":\"resttest\",\"number\":1}"));
    }

    @Test
    public void testRestList() throws Exception {
        String response = template.getForObject("/rlist", String.class);
        assertThat(response, containsString("rlist"));
    }

    @Test
    public void testRestListAdd() throws Exception {
        String response = template.getForObject("/rlist-add", String.class);
        assertThat(response, containsString("rlist-add"));
    }

}
