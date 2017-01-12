package com.gw.apex.hack.yabe.service;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.VendorRepo;
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

    public static final String LIST_TEMPLATE = "list";
    public static final String ALIVE_TEMPLATE = "alive";
    public static final String HOME_TEMPLATE = "home";
    public static final String BUYER_TEMPLATE = "buyer";
    private static final String NAVBAR_TEMPLATE = "navbar";

    @Autowired
    BuyerRepo buyerRepo;

    @Autowired
    VendorRepo vendorRepo;

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
        return HOME_TEMPLATE;
    }

    @RequestMapping("/alive")
    public String alive(Model model) {
        model.addAttribute("date", new Date());
        return ALIVE_TEMPLATE;
    }

    @RequestMapping("/buyer")
    public String buyer(Model model) {
        return BUYER_TEMPLATE;
    }


    @RequestMapping("/navbar")
    public String navbar(Model model) {
        return NAVBAR_TEMPLATE;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("all", buyerRepo.findAll());
        return LIST_TEMPLATE;
    }

    @RequestMapping("/addToList")
    public String addToList(
            @RequestParam(value="name") String name,
            @RequestParam(value="number") int number,
            Model model) {

        buyerRepo.save(new Buyer(name, number));
        model.addAttribute("all", buyerRepo.findAll());

        return HOME_TEMPLATE;
    }

    @RequestMapping("/removeFromList")
    public String listRemove(
            @RequestParam(value="name") String name,
            Model model) {
        buyerRepo.delete(name);
        model.addAttribute("all", buyerRepo.findAll());

        return HOME_TEMPLATE;
    }
}
