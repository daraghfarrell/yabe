package com.gw.apex.hack.yabe;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author dfarrell on 12/12/2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class TestWebControllers {


    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".html");

        mvc = MockMvcBuilders.standaloneSetup(new WebController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testWebControllerAliveIs200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/alive").accept(MediaType.ALL))
                .andExpect(status().is2xxSuccessful());
    }
}
