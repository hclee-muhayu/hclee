package com.muhayu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by hyecheon on 2017. 3. 7..
 */
@Controller
public class UserController {

    @PostMapping("/create")
    public String create(User user) {
        System.out.println(user.toString());
        return "index";
    }
}
