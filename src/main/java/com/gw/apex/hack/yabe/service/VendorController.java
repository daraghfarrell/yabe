package com.gw.apex.hack.yabe.service;

import com.gw.apex.hack.yabe.domain.RequestToBuy;
import com.gw.apex.hack.yabe.domain.RequestToSell;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import com.gw.apex.hack.yabe.repo.RequestToBuyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author dfarrell on 12/12/2016.
 */

@Controller
@Transactional
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
        model.addAttribute("deals", getRequestToBuysDeals());
        return "deals";
    }

    private ArrayList<RequestToBuy> getRequestToBuysDeals() {
        ArrayList deals = new ArrayList();
        Iterator<RequestToBuy> rtbs = rtbRepo.findAll().iterator();
        while(rtbs.hasNext()) {
            RequestToBuy requestToBuy = rtbs.next();
            if (requestToBuy.isVendorOffers()) {
                deals.add(requestToBuy);
            }
        }
        return deals;

    }

    @RequestMapping("/vendorAcceptRTB")
    public String vendorAcceptRTB(
            @RequestParam(value="id") long id,
            Model model) {
        RequestToBuy requestToBuy = rtbRepo.findOne(id);
        requestToBuy.addRequestToSell(new RequestToSell());
        requestToBuy.setVendorOffers(true);
        rtbRepo.save(requestToBuy);

        model.addAttribute("deals", getRequestToBuysDeals());

        return "redirect:deals";
    }
}
