package com.muhayu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyecheon on 2017. 3. 7..
 */
@Controller
public class UserController {
    final private List<User> users = new ArrayList<>();

    @PostMapping("/create")
    public String create(User user) {
        System.out.println(user.toString());
        users.add(user);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", users);
        return "list";
    }
}
