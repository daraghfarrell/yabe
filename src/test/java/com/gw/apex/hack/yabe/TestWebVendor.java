package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Item;
import com.gw.apex.hack.yabe.domain.RequestToBuy;
import com.gw.apex.hack.yabe.domain.RequestToSell;
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

    @Test
    public void testRTBViewDetails () {
        String buyerName = "buyer4328";
        Item item = new Item();
        RequestToBuy rtb = new RequestToBuy();
        rtb.setItem(item);
        rtb.setUser(new Buyer(buyerName));
        rtb.setVendorOffers(true);
        rtb = rtbRepo.save(rtb);


//        String buyer2 = "buyer2"

        String response = template.getForObject("/viewRTB?id="+rtb.getId(), String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("Request To Buy Details"));
        assertThat(response, containsString(buyerName));

    }

    @Test
    public void testDealsDisplaysCurrentDeals() {
        String buyerName = "buyer4328";
        Item item = new Item();

        RequestToBuy rtb = new RequestToBuy();
        rtb.setItem(item);
        rtb.setUser(new Buyer(buyerName));
        rtb.addRequestToSell(new RequestToSell());
        rtb.setVendorOffers(true);

        rtbRepo.save(rtb);

        String buyer2 = "buyer3213123";
        RequestToBuy rtb2 = new RequestToBuy();
        rtb2.setItem(new Item());
        rtb2.setUser(new Buyer(buyer2));
        rtb2.setVendorOffers(false);
        rtbRepo.save(rtb2);

        String response = template.getForObject("/deals", String.class);
        assertThat(response.contains("404"), is(false));
        assertThat(response, containsString("Current Deals"));
        assertThat(response, containsString(buyerName));
        assertThat(response.contains(buyer2), is(false));

        rtbRepo.delete(rtb);
    }

}
