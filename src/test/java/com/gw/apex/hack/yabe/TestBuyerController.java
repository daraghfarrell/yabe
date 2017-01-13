package com.gw.apex.hack.yabe;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Item;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.ItemRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

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
                .andExpect(model().attributeExists("buyers"));

    }
}
