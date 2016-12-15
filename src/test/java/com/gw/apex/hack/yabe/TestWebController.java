package com.gw.apex.hack.yabe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author dfarrell on 12/12/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWebController {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testIsAlive() throws Exception {
        String response = template.getForObject("/alive", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("Hello"));
    }

    @Test
    public void testHomePageExists() throws Exception {
        String response = template.getForObject("/", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("<title>YABE</title>"));
    }

    @Test
    public void testListReturnsEmptyListWhenNothingAdded() throws Exception {
        String response = template.getForObject("/list", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("<title>List</title>"));
    }

    @Test
    public void testListAddAndDelete() throws Exception {
        String response = template.getForObject("/list", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("<title>List</title>"));

        response = template.getForObject("/addToList?name=test567&number=567", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("test567"));

        response = template.getForObject("/removeFromList?name=test567", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response.contains("test567"), is(false));
    }

    @Test
    public void testHome2Displays() {
        String response = template.getForObject("/home2", String.class);
        String expected = "<title>YABE Home2</title>";
        assertThat(response, containsString(expected));
    }
}
