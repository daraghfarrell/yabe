package com.gw.apex.hack.yabe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author dfarrell on 12/12/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWebControllerToDBIntegration {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private AnyDomainRepo anyDomainRepo;

    @Test
    public void testWeCanDisplayAListonAWebPage() throws Exception {
        String name0 = "Name0";
        String name1 = "Name1";
        AnyDomainOne any0 = new AnyDomainOne(name0, 0);
        AnyDomainOne any1 = new AnyDomainOne(name1, 1);

        anyDomainRepo.save(any0);
        anyDomainRepo.save(any1);

        assertThat(anyDomainRepo.findAll().spliterator().getExactSizeIfKnown(), is(2L));

        String response = template.getForObject("/list", String.class);
        assertThat(response, containsString("List:"));
        assertThat(response, containsString(name0));
        assertThat(response, containsString(name1));
    }

    @Test
    public void testWeCanAddToTheListOnTheWebPage() throws Exception {
        String response = template.getForObject("/addToList?name=Fred&number=1234", String.class);
        assertThat(response, containsString("List:"));
        assertThat(response, containsString("Fred"));
        assertThat(response, containsString("1234"));
    }

}
