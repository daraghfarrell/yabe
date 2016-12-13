package com.gw.apex.hack.yabe;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dfarrell on 13/12/2016.
 */

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/anydata")
    public AnyDomainOne anydata() {
        return new AnyDomainOne("resttest", 1);
    }

}
