package com.gw.apex.hack.yabe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author dfarrell on 12/12/2016.
 */

@Controller
public class WebController {

    @RequestMapping("/alive")
    public String alive(Model model) {
        model.addAttribute("date", new Date());
        return "alive";
    }

}
