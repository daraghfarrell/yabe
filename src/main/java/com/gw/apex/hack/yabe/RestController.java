package com.gw.apex.hack.yabe;

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
    AnyDomainRepo anyDomainRepo;

    @RequestMapping("/rlive")
    public String rlive() {
        return "\n*****\nrlive: "+new Date()+"\n*****\n";
    }

    @RequestMapping("/rlist")
    public Iterable<AnyDomainOne> rlist() {
        return anyDomainRepo.findAll();
    }

    @RequestMapping("/rlist-add")
    public Iterable<AnyDomainOne> rlistAdd(
            @RequestParam(value="name") String name,
            @RequestParam(value="number") int number) {
        anyDomainRepo.save(new AnyDomainOne(name, number));
        return anyDomainRepo.findAll();
    }

    @RequestMapping("/rlist-remove")
    public Iterable<AnyDomainOne>rlistRemove(
            @RequestParam(value="name") String name) {
        anyDomainRepo.delete(name);
        return anyDomainRepo.findAll();
    }
}
