package com.gw.apex.hack.yabe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
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
    public void testRestControllerAlive() throws Exception {
        String response = template.getForObject("/rlive", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response.contains("rlive"), is(true));
    }

    @Test
    public void testRestListAdd() throws Exception {
        String response = template.getForObject("/rlist-add", String.class);
        assertThat(response.contains("400"), is(true));

        response = template.getForObject("/rlist-add?name=test94746&number=94746", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response.contains("test94746"), is(true));
        assertThat(response.contains("94746"), is(true));

        response = template.getForObject("/rlist-remove?name=test94746", String.class);

    }

    @Test
    public void testRestListRemove() throws Exception {
        String response = template.getForObject("/rlist-remove", String.class);
        assertThat(response.contains("400"), is(true));

        response = template.getForObject("/rlist-remove?name=123", String.class);
        assertThat(response, containsString("[]"));

        response = template.getForObject("/rlist-add?name=test4324&number=4324", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response.contains("test4324"), is(true));
        assertThat(response.contains("4324"), is(true));

        response = template.getForObject("/rlist-remove?name=test4324", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response.contains("test4324"), is(false));
        assertThat(response.contains("4324"), is(false));

    }

    @Test
    public void testRestList() throws Exception {
        String response = template.getForObject("/rlist", String.class);
        assertThat(response, containsString("[]"));
        assertThat(response.contains("[]"), is(true));

        template.getForObject("/rlist-add?name=test1&number=123", String.class);
        template.getForObject("/rlist-add?name=test2&number=223", String.class);
        template.getForObject("/rlist-add?name=test3&number=323", String.class);

        response = template.getForObject("/rlist", String.class);
        assertThat(response.contains("[]"), is(false));
        assertThat(response.contains("404"), is(false));

        assertThat(response, containsString("test1"));
        assertThat(response, containsString("test2"));
        assertThat(response, containsString("test3"));
    }
}
