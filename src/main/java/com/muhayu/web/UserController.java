package com.muhayu.web;

import com.muhayu.domain.User;
import com.muhayu.domain.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyecheon on 2017. 3. 7..
 */
@Log4j
@Controller
@RequestMapping("/users")
public class UserController {
    final private List<User> users = new ArrayList<>();


    @Autowired
    private UserRepository repository;

    @PostMapping("")
    public String create(User user) {
        log.info(user);
        repository.save(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", repository.findAll());
        return "user/list";
    }
}
