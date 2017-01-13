package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Item;
import com.gw.apex.hack.yabe.domain.RequestToBuy;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;
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
public class TestWebVendor {
    @Autowired
    private TestRestTemplate template;

    @Autowired
    RequestToBuyRepo rtbRepo;



    @Test
    public void testVendorPageExists() throws Exception {
        String response = template.getForObject("/vendor", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("<title>Vendor</title>"));
    }

    @Test
    public void testRequestToBuyListIsDisplayed() {
        String buyerName = "buyer4328";
        Item item = new Item();

        RequestToBuy rtb = new RequestToBuy();
        rtb.setItem(item);
        rtb.setUser(new Buyer(buyerName));

        rtb = rtbRepo.save(rtb);

        String response = template.getForObject("/vendor", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("Open Request To Buys"));
        assertThat(response, containsString(buyerName));
    }
}
