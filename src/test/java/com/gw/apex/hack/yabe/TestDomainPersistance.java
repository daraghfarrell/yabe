package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.repo.AnyDomainRepo;
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
public class TestDomainOneGemfire {
    @Autowired
    AnyDomainRepo anyDomainRepo;

    @Test
    public void testWeCanCreateAndStoreADomainObject() {
        String name0 = "Name0";
        String name1 = "Name1";
        Buyer any0 = new Buyer(name0, 0);
        Buyer any1 = new Buyer(name1, 1);

        anyDomainRepo.save(any0);
        anyDomainRepo.save(any1);

        Buyer resultA = anyDomainRepo.findByName(name0);
        assertThat(name0, is(resultA.getName()));

        Buyer resultB = anyDomainRepo.findByName(name1);
        assertThat(name1, is(resultB.getName()));

        assertThat(name0, not(resultB));
        assertThat(name1, not(resultA));

        anyDomainRepo.delete(name0);
        anyDomainRepo.delete(name1);
    }

}
