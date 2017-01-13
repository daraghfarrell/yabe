package com.gw.apex.hack.yabe.service;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmanning on 13/01/2017.
 */
@Controller
public class BuyerController {

    @Autowired
    public BuyerRepo buyerRepo;

    @ModelAttribute("buyers")
    public Iterable<Buyer> populateBuyers(){
        return buyerRepo.findAll();
    }

    @RequestMapping("/buyer")
    public String buyer(Model model) {
        return "buyer";
    }
}
