package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.*;
import com.gw.apex.hack.yabe.repo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author dfarrell on 13/12/2016.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TestDomainPersistance {
    @Autowired
    BuyerRepo buyerRepo;

    @Autowired
    VendorRepo vendorRepo;

    @Autowired
    RequestToBuyRepo rtbRepo;

    @Autowired
    RequestToSellRepo rtsRepo;

    @Autowired
    ItemRepo itemRepo;

    @Test
    public void testBuyerCreateAndStore() {
        String name0 = "Buyer0";
        String name1 = "Buyer1";
        Buyer buyer0 = new Buyer(name0);
        Buyer buyer1 = new Buyer(name1);

        buyerRepo.save(buyer0);
        buyerRepo.save(buyer1);

        Buyer resultA = buyerRepo.findByName(name0);
        assertThat(name0, is(resultA.getName()));

        Buyer resultB = buyerRepo.findByName(name1);
        assertThat(name1, is(resultB.getName()));

        assertThat(name0, not(resultB.getName()));
        assertThat(name1, not(resultA.getName()));

        buyerRepo.delete(buyer0);
        buyerRepo.delete(buyer1);
    }

    @Test
    public void testVendorCreateAndStore() {
        String name1 = "Vendor01";
        String name2 = "Vendor02";

        Vendor vendor1 = new Vendor(name1);
        Vendor vendor2 = new Vendor(name2);

        vendorRepo.save(vendor1);
        vendorRepo.save(vendor2);

        Vendor resultA = vendorRepo.findByName(name1);
        assertThat(name1, is (resultA.getName()));

        Vendor resultB = vendorRepo.findByName(name2);
        assertThat(name2, is (resultB.getName()));

        assertThat(name1, not(resultB.getName()));
        assertThat(name2, not(resultA.getName()));

        vendorRepo.delete(vendor1);
        vendorRepo.delete(vendor2);
    }

    @Test
    public void testRequestToBuyCreateAndStore(){
        String buyerName = "buyer";
        Item item = new Item();

        RequestToBuy rtb = new RequestToBuy();
        rtb.setItem(item);
        rtb.setUser(new Buyer("buyer"));

        rtb = rtbRepo.save(rtb);

        RequestToBuy retrievedRTB = rtbRepo.findOne(rtb.getId());
        Item retrievedItem = retrievedRTB.getItem();

        assertThat(retrievedRTB.getUser().getName(), is(buyerName));

        String vendorName = "Vendor";

        RequestToSell rts = new RequestToSell();
        rts.setItem(itemRepo.findOne(retrievedItem.getId()));
        rts.setUser(new Vendor(vendorName));
        rts.addRequestToBuy(retrievedRTB);

        rts = rtsRepo.save(rts);

        RequestToSell retrievedRTS = rtsRepo.findOne(rts.getId());

        assertThat(retrievedRTS.getUser().getName(), is(vendorName));
        assertThat(retrievedRTS.getRequestToBuys().get(0).getId(), is(retrievedRTB.getId()));

        retrievedRTB.addRequestToSell(rtsRepo.findOne(rts.getId()));
        rtbRepo.save(retrievedRTB);
        retrievedRTB = rtbRepo.findOne(rtb.getId());
        assertThat(retrievedRTB.getRequestToSells().get(0).getId(), is(retrievedRTS.getId()));

        rtbRepo.delete(retrievedRTB);
//        rtsRepo.delete(retrievedRTS);
    }
}
