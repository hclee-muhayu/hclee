package com.muhayu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.annotation.HandlesTypes;

/**
 * Created by hyecheon on 2017. 3. 7..
 */

@Controller
public class WelcomeController {
    @GetMapping("/helloworld")
    public String welcome(Model model) {
        model.addAttribute("name", "javajigi");
        model.addAttribute("value", 10000);
        model.addAttribute("taxed_value", 30);
        model.addAttribute("in_ca", true);
        return "welcome";
    }
}
