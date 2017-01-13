package com.gw.apex.hack.yabe.service;

import com.gw.apex.hack.yabe.domain.RequestToBuy;
import com.gw.apex.hack.yabe.domain.RequestToSell;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * @author dfarrell on 12/12/2016.
 */

@Controller
public class VendorController {

    private static final String VENDOR_TEMPLATE = "vendor";

    @Autowired
    BuyerRepo buyerRepo;

    @Autowired
    RequestToBuyRepo rtbRepo;

    @RequestMapping("/vendor")
    public String vendor(Model model) {
        model.addAttribute("all", rtbRepo.findAll());
        return VENDOR_TEMPLATE;
    }


    @RequestMapping("/viewRTB")
    public String viewRTB(
        @RequestParam(value="id") long id,
        Model model) {
        model.addAttribute("rtb", rtbRepo.findOne(id));
        return "viewRTB";
    }


    @RequestMapping("/deals")
    public String deals(Model model) {
        ArrayList deals = new ArrayList();
        Iterable<RequestToBuy> rtbs = rtbRepo.findAll();

        for(RequestToBuy item : rtbs) {
            if (item.getRequestToSells().size() > 0) {
                deals.add(item);
            }
        }

        model.addAttribute("deals", deals);
        return "deals";
    }


    @RequestMapping("/vendorAcceptRTB")
    public String vendorAcceptRTB(
            @RequestParam(value="id") long id,
            Model model) {
        RequestToBuy requestToBuy = rtbRepo.findOne(id);
        requestToBuy.getRequestToSells().add(new RequestToSell());
        return "deals";
    }


//    @RequestMapping("/addToList")
//    public String addToList(
//            @RequestParam(value="name") String name,
//            @RequestParam(value="number") int number,
//            Model model) {
//
//        Buyer entity = new Buyer(name);
//        buyerRepo.save(entity);
//        model.addAttribute("all", buyerRepo.findAll());
//
//        return LIST_TEMPLATE;
//    }
//
//    @RequestMapping("/removeFromList")
//    public String listRemove(
//            @RequestParam(value="name") String name,
//            Model model) {
//        buyerRepo.delete(name);
//        model.addAttribute("all", buyerRepo.findAll());
//
//        return HOME_TEMPLATE;
//    }
}
