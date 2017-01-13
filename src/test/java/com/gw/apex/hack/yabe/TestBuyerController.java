package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Item;
import com.gw.apex.hack.yabe.formobjects.RequestToBuyForm;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.ItemRepo;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by wmanning on 13/01/2017.
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBuyerController {

    @Autowired
    public ItemRepo itemRepo;

    @Autowired
    public BuyerRepo buyerRepo;

    @Autowired
    public RequestToBuyRepo requestToBuyRepo;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        itemRepo.save(new Item("ItemOne"));
        itemRepo.save(new Item("ItemTwo"));
        itemRepo.save(new Item("ItemThree"));

        buyerRepo.save(new Buyer("BuyerOne"));
        buyerRepo.save(new Buyer("BuyerTwo"));
        buyerRepo.save(new Buyer("BuyerThree"));
    }

    @Test
    public void testLoadPage() throws Exception{
        this.mockMvc.perform(get("/buyer"))
                .andExpect(model().attributeExists("buyers"))
                .andExpect(model().attributeExists("requestToBuyForm"));

    }

    @Test
    public void formSubmission() throws Exception{

        RequestToBuyForm requestToBuyForm = new RequestToBuyForm();
        requestToBuyForm.buyerId = "1";
        requestToBuyForm.itemId = "1";

        this.mockMvc.perform(post("/buyer").param("buyerId", "1").param("itemId","1"))
                .andReturn();

        assertThat(Lists.newArrayList(requestToBuyRepo.findAll()).get(0).getUser().getId(), is(new Long(1)));
    }
}
