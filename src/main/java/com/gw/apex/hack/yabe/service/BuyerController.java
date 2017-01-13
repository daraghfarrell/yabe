package com.gw.apex.hack.yabe.service;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.domain.Item;
import com.gw.apex.hack.yabe.domain.RequestToBuy;
import com.gw.apex.hack.yabe.formobjects.RequestToBuyForm;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.ItemRepo;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    public ItemRepo itemRepo;

    @Autowired
    public RequestToBuyRepo requestToBuyRepo;

    @ModelAttribute("buyers")
    public Iterable<Buyer> populateBuyers(){
        return buyerRepo.findAll();
    }

    @ModelAttribute("items")
    public Iterable<Item> populateItems(){
        return itemRepo.findAll();
    }

    @ModelAttribute("requestToBuyForm")
    public RequestToBuyForm populateRequestToBuyForm() {
        return new RequestToBuyForm();
    }

    @GetMapping("/buyer")
    public String buyer(Model model) {
        return "buyer";
    }

    @PostMapping("/buyer")
    public String buyerSubmit(@ModelAttribute RequestToBuyForm requestToBuyForm){

        RequestToBuy requestToBuy = new RequestToBuy();
        requestToBuy.setItem(itemRepo.findOne(new Long(requestToBuyForm.itemId)));
        requestToBuy.setUser(buyerRepo.findOne(new Long(requestToBuyForm.buyerId)));

        requestToBuyRepo.save(requestToBuy);
        return "redirect:vendor";
    }
}
