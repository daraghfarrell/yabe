package com.gw.apex.hack.yabe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dfarrell on 13/12/2016.
 */

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    AnyDomainRepo anyDomainRepo;

    @RequestMapping("/alive")
    public String anydata() {
        return "alive";
    }

    @RequestMapping("/jlist")
    public Iterable<AnyDomainOne> jlist() {
        return anyDomainRepo.findAll();
    }

}
