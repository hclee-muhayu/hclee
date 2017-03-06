package com.muhayu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hyecheon on 2017. 3. 7..
 */
@Controller
public class WelcomeController {
    @GetMapping("/helloworld")
    public String welcome(String name, int age, Model model) {
        System.out.println(name + "name");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "welcome";
    }
}
