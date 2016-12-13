package com.gw.apex.hack.yabe;

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

public class AnyDomainOneGemfireTest {
    @Autowired
    AnyDomainRepo anyDomainRepo;

    @Test
    public void testWeCanCreate() {
        String name0 = "Name0";
        String name1 = "Name1";
        AnyDomainOne any0 = new AnyDomainOne(name0, 0);
        AnyDomainOne any1 = new AnyDomainOne(name1, 1);

        anyDomainRepo.save(any0);
        anyDomainRepo.save(any1);

        AnyDomainOne resultA = anyDomainRepo.findByName(name0);
        assertThat(name0, is(resultA.getName()));

        AnyDomainOne resultB = anyDomainRepo.findByName(name1);
        assertThat(name1, is(resultB.getName()));

        assertThat(name0, not(resultB));
        assertThat(name1, not(resultA));
    }

}
