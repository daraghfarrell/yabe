package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
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
public class TestWebControllerToGemfireIntegration {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private BuyerRepo buyerRepo;

    @Test
    public void testWeCanDisplayDomainObjectListonAWebPage() throws Exception {
        String name0 = "Name0";
        String name1 = "Name1";
        Buyer any0 = new Buyer(name0, 0);
        Buyer any1 = new Buyer(name1, 1);

        buyerRepo.save(any0);
        buyerRepo.save(any1);

        String response = template.getForObject("/list", String.class);
        assertThat(response, containsString("List:"));
        assertThat(response, containsString(name0));
        assertThat(response, containsString(name1));

        buyerRepo.delete(name0);
        buyerRepo.delete(name1);
    }

    @Test
    public void testWeCanAddToTheListOnTheWebPage() throws Exception {
        String response = template.getForObject("/addToList?name=Fred&number=1234", String.class);
        assertThat(response, containsString("Fred"));
        assertThat(response, containsString("1234"));
    }

}
