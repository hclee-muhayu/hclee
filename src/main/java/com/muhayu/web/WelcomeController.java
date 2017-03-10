package com.muhayu.web;

import com.muhayu.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyecheon on 2017. 3. 7..
 */

@Controller
public class WelcomeController {
    @GetMapping("/helloworld")
    public String welcome(Model model, User user) {
        user.setEmail("helloWorld");
        model.addAttribute("user", user);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User());
        }
        model.addAttribute("userList", userList);
        return "welcome";
    }
}
