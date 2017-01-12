package com.gw.apex.hack.yabe.service;

import com.gw.apex.hack.yabe.domain.Buyer;
import com.gw.apex.hack.yabe.repo.BuyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author dfarrell on 13/12/2016.
 */

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    BuyerRepo buyerRepo;
    private Buyer entity;

    @RequestMapping("/rlive")
    public String rlive() {
        return "\n*****\nrlive: "+new Date()+"\n*****\n";
    }

    @RequestMapping("/rlist")
    public Iterable<Buyer> rlist() {
        return buyerRepo.findAll();
    }

    @RequestMapping("/rlist-add")
    public Iterable<Buyer> rlistAdd(
            @RequestParam(value="name") String name,
            @RequestParam(value="number") int number) {
        entity = new Buyer(name);
        buyerRepo.save(entity);
        return buyerRepo.findAll();
    }

    @RequestMapping("/rlist-remove")
    public Iterable<Buyer>rlistRemove(
            @RequestParam(value="name") String name) {
        buyerRepo.delete(name);
        return buyerRepo.findAll();
    }
}
