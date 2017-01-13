package com.gw.apex.hack.yabe.service;

import com.gw.apex.hack.yabe.SampleDataLoader;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.ItemRepo;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author dfarrell on 12/12/2016.
 */

@Controller
public class WebController {

    private static final String LIST_TEMPLATE = "list";
    private static final String ALIVE_TEMPLATE = "alive";
    private static final String HOME_TEMPLATE = "home";
    private static final String NAVBAR_TEMPLATE = "navbar";
    public static final String HEADER_TEMPLATE = "header";
    public static final String TAIL_TEMPLATE = "tail";

    @Autowired
    BuyerRepo buyerRepo;

    @Autowired
    RequestToBuyRepo rtbRepo;

    @Autowired
    ItemRepo itemRepo;

    @RequestMapping("/bootstrap-exs")
    public String btest(Model model) {
        return "bootstrap-exs";
    }

    @RequestMapping("/home")
    public String home2(Model model) {
        model.addAttribute("all", buyerRepo.findAll());
        return HOME_TEMPLATE;
    }

    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }


    @RequestMapping("/navbar")
    public String navbar(Model model) {
        return NAVBAR_TEMPLATE;
    }

    @RequestMapping("/header")
    public String header(Model model) {
        return HEADER_TEMPLATE;
    }

    @RequestMapping("/tail")
    public String tail(Model model) {
        return TAIL_TEMPLATE;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("all", buyerRepo.findAll());
        return LIST_TEMPLATE;
    }

    @RequestMapping("/alive")
    public String alive(Model model) {
        model.addAttribute("date", new Date());
        return ALIVE_TEMPLATE;
    }


    @RequestMapping("/removeFromList")
    public String listRemove(
            @RequestParam(value="name") String name,
            Model model) {
        //xbuyerRepo.delete(name);
        model.addAttribute("all", buyerRepo.findAll());

        return HOME_TEMPLATE;

    }

    @RequestMapping("/loadAll")
    public String loadAll(Model model) {

        SampleDataLoader sampleDataLoader = new SampleDataLoader();
        sampleDataLoader.loadRequestToBuy(rtbRepo);
//        sampleDataLoader.loadItems(itemRepo);
//        sampleDataLoader.loadBuyers(buyerRepo);

        return HOME_TEMPLATE;
    }
}
