package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Vendor;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.VendorRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author dfarrell on 13/12/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDomainPersistance {
    @Autowired
    BuyerRepo buyerRepo;

    @Autowired
    VendorRepo vendorRepo;

    @Test
    public void testBuyerCreateAndStore() {
        String name0 = "Buyer0";
        String name1 = "Buyer1";
        Buyer any0 = new Buyer(name0, 0);
        Buyer any1 = new Buyer(name1, 1);

        buyerRepo.save(any0);
        buyerRepo.save(any1);

        Buyer resultA = buyerRepo.findByName(name0);
        assertThat(name0, is(resultA.getName()));

        Buyer resultB = buyerRepo.findByName(name1);
        assertThat(name1, is(resultB.getName()));

        assertThat(name0, not(resultB.getName()));
        assertThat(name1, not(resultA.getName()));

        buyerRepo.delete(name0);
        buyerRepo.delete(name1);
    }

    @Test
    public void testVendorCreateAndStore() {
        String name1 = "Vendor01";
        String name2 = "Vendor02";

        Vendor vendor1 = new Vendor(name1, 1);
        Vendor vendor2 = new Vendor(name2, 2);

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

}
